package view;

import common.BackGround;
import common.Barrier;
import common.Bird;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import static common.Constant.*;

public class MainFrame extends Frame {
    private BufferedImage bufferedImage =
            new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
    private boolean isGameOver;
    private Bird bird;
    private List<Barrier> barriers;
    private BackGround backGround;

    public MainFrame() {
        setVisible(true);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setTitle(FRAME_TITLE);
        // frame's initialization position
        setLocation(FRAME_X, FRAME_Y);
        // frame's size is not allowed to change
        setResizable(false);
        backGround = new BackGround();
    }

    public void draw(boolean isGameOver, Bird bird, List<Barrier> barriers) {
        this.isGameOver = isGameOver;
        this.bird = bird;
        this.barriers = barriers;
        repaint();
    }

    /**
     * If game is not over, draw the bird and barriers.
     * Else, draw a "Game Over" string.
     * @param g
     */
    @Override
    public void update(Graphics g) {
        if (!isGameOver) {
            // get the pen of the image
            Graphics graphics = bufferedImage.getGraphics();
            // draw background, bird and barriers
            backGround.draw(graphics);
            if (bird != null) {
                drawBird(graphics, bird);
            }
            if (barriers != null) {
                for (Barrier b: barriers) {
                    drawBarrier(graphics, b);
                }
            }
            // draw all pics on screen at the same time
            g.drawImage(bufferedImage, 0, 0, null);
        } else {
            String gameOver = "Game Over!";
            g.setColor(Color.red);
            g.setFont(new Font("Microsoft Yahei", 1, 52));
            g.drawString(gameOver, 150, 275);
        }
    }

    private void drawBird(Graphics g, Bird bird) {
        g.drawImage(bird.img, bird.x, bird.y, null);
        g.setColor(Color.GRAY);
        g.drawRect(bird.x, bird.y, bird.rect.width, bird.rect.height);
    }

    private void drawBarrier(Graphics g, Barrier barrier) {
        if (barrier.isTopBar) {
            drawTopBar(g, barrier);
        } else {
            drawBotBar(g, barrier);
        }
        g.setColor(Color.GRAY);
        g.drawRect(barrier.x, barrier.y, barrier.width, barrier.height);
    }

    private void drawTopBar(Graphics g, Barrier b) {
        int num = (b.height - Barrier.BARRIER_HEAD_HEIGHT)
                / Barrier.BARRIER_HEIGHT + 1;
        // draw the middle part
        for (int i = 0; i < num; i++) {
            g.drawImage(Barrier.img[0], b.x,
                    b.y + Barrier.BARRIER_HEIGHT * i, null);
        }
        // draw the head
        int xCo = b.x - (Barrier.BARRIER_HEAD_WIDTH - Barrier.BARRIER_WIDTH) / 2;
        int yCo = b.height - Barrier.BARRIER_HEAD_HEIGHT;
        g.drawImage(Barrier.img[2], xCo, yCo, null);
    }

    private void drawBotBar(Graphics g, Barrier b) {
        int num  = b.height / Barrier.BARRIER_HEIGHT + 1;
        // draw the middle part
        for (int i = 0; i < num; i++) {
            g.drawImage(Barrier.img[0], b.x,
                    FRAME_HEIGHT - Barrier.BARRIER_HEIGHT * i, null);
        }
        // draw the head
        int xCo = b.x - (Barrier.BARRIER_HEAD_WIDTH - Barrier.BARRIER_WIDTH) / 2;
        int yCo = FRAME_HEIGHT - b.height;
        g.drawImage(Barrier.img[1], xCo, yCo, null);
    }
}
