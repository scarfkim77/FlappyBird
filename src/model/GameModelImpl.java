package model;

import common.Barrier;
import common.Bird;
import common.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameModelImpl implements GameModel {
    private Bird bird;
    private List<Barrier> barriers;
    private Random rand;

    public GameModelImpl() {
        this.bird = new Bird();
        this.barriers = new ArrayList<>();
        this.rand = new Random();
    }

    @Override
    public void run(int direction) {
        bird.changeDirection(direction);
        bird.fly();
        refreshBarriers();
        for (Barrier b: barriers) {
            b.move();
        }
        removeBarriers();
        checkCollision();
    }

    /**
     * If there is no barrier, or the last two barriers have come to mid of frame,
     * refresh a new group of barriers.
     */
    private void refreshBarriers() {
        int[] heightsOfTwoBars = randomHeight();
        int size = barriers.size();
        if (size == 0 || barriers.get(size - 1).hasComeToMid()) {
            Barrier topToBot = new Barrier(heightsOfTwoBars[0], true);
            Barrier botToTop = new Barrier(heightsOfTwoBars[1], false);
            barriers.add(topToBot);
            barriers.add(botToTop);
        }
    }

    /**
     * If the barrier has been passed by the bird, and become invisible,
     * then remove it from the barrier list, avoid too many barriers in the list.
     */
    private void removeBarriers() {
        for (int i = 0; i < barriers.size(); i++) {
            if (!barriers.get(i).isVisible) {
                barriers.remove(i);
                i--;
            }
        }
    }

    /**
     * Randomly generate two barriers' height in range[100, 500).
     * If they are too closed to each other, regenerate again.
     * @return an int array with size 2
     */
    private int[] randomHeight() {
        // randomly generate a barrier's height in range [100, 500)
        int heightOfTopBar = rand.nextInt(400) + 100;
        int heightOfBotBar = rand.nextInt(400) + 100;
        if (heightOfTopBar + heightOfBotBar <= Constant.FRAME_HEIGHT - 75) {
            return new int[]{heightOfTopBar, heightOfBotBar};
        }
        return randomHeight();
    }

    /**
     * If the bird collides with a barrier, then game is over.
     * Check if the rectangle of bird has an intersection with the barrier's rectangle.
     */
    private void checkCollision() {
        for (Barrier b: barriers) {
            if (this.bird.rect.intersects(b.rect)) {
                this.bird.isAlive = false;
            }
        }
    }

    @Override
    public boolean isGameOver() {
        return !bird.isAlive;
    }

    @Override
    public Bird getBird() {
        return bird;
    }

    @Override
    public List<Barrier> getBarriers() {
        return barriers;
    }
}
