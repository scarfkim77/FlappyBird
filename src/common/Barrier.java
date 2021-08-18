package common;

import view.GameUtil;

import static common.Constant.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Barrier {
    public static BufferedImage[] img;
    public static final int NUM = 3;
    public static int BARRIER_WIDTH;
    public static int BARRIER_HEIGHT;
    public static int BARRIER_HEAD_WIDTH;
    public static int BARRIER_HEAD_HEIGHT;

    // isTopToBot == true, means this barrier will be drawn from top to bot
    // else, it will be drawn from bottom to top
    public boolean isTopToBot;
    // barrier's location
    public int x = FRAME_WIDTH; // new barrier will always appear from rightest
    public int y; // differs from the barrier's direction
    // the width and height of a barrier
    public int width, height;
    public boolean isVisible;
    // barrier's moving speed
    private int speed = 3;
    public Rectangle rect;


    public Barrier(int height, boolean isTopToBot) {
        img = new BufferedImage[NUM];
        for (int i = 0; i < NUM; i++) {
            img[i] = GameUtil.loadBufferedImage(BARRIER_IMG[i]);
        }
        BARRIER_WIDTH = img[0].getWidth();
        BARRIER_HEIGHT = img[0].getHeight();
        BARRIER_HEAD_WIDTH = img[1].getWidth();
        BARRIER_HEAD_HEIGHT = img[1].getHeight();
        if (isTopToBot) y = 0;
        else y = FRAME_HEIGHT - height;
        this.width = BARRIER_WIDTH;
        this.height = height;
        this.isTopToBot = isTopToBot;
        isVisible = true;
        rect = new Rectangle(BARRIER_WIDTH, height);
    }

    public void move() {
        x -= speed;
        if (x < 35) {
            isVisible  = false;
        }
        rectMove();
    }

    /**
     * ake the rectangle move along with the barrier.
     */
    public void rectMove() {
        rect.x = x;
        rect.y = y;
    }

    /**
     * If the last group of barriers has come to the center of the screen,
     * then refresh the next group of barriers.
     * @return true if they have moved more than 175 pixels, false otherwise
     */
    public boolean hasCameToMid() {
        return FRAME_WIDTH - x > 175;
    }
}