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
        // if the barrier is out of screen, then remove it from the list
        // avoid the list containing too many barriers
        for (int i = 0; i < barriers.size(); i++) {
            if (!barriers.get(i).isVisible) {
                barriers.remove(i);
                i--;
            }
        }
        checkCollision();
    }

    /**
     * Conditionally refresh a group of barriers.
     */
    private void refreshBarriers() {
        int[] heightsOfTwoBars = randomHeight();
        int size = barriers.size();
        if (size == 0 || barriers.get(size - 1).hasCameToMid()) {
            Barrier topToBot = new Barrier(heightsOfTwoBars[0], true);
            Barrier botToTop = new Barrier(heightsOfTwoBars[1], false);
            barriers.add(topToBot);
            barriers.add(botToTop);
        }
    }

    private int[] randomHeight() {
        // randomly generate a barrier's height in range [100, 500)
        int heightOfTopBar = rand.nextInt(400) + 100;
        int heightOfBotBar = rand.nextInt(400) + 100;
        // if two barriers meets, then regenerate again
        if (heightOfTopBar + heightOfBotBar > Constant.FRAME_HEIGHT - 75) {
            randomHeight();
        }
        return new int[]{heightOfTopBar, heightOfBotBar};
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
        return bird.isAlive;
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
