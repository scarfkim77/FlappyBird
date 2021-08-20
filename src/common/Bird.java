package common;

import java.awt.*;
import java.awt.image.BufferedImage;

import static common.Constant.*;

public class Bird {
    // bird's image
    public BufferedImage img = LoadImage.loadBufferedImage(BIRD_IMG);
    // bird's location
    public int x = 200, y = 200;
    // bird's flying direction:
    // isUpward == true, means bird flies upward; downward otherwise
    public boolean isUpward = false;
    // bird's flying speed
    public int speed = 5;
    public boolean isAlive = true;
    public Rectangle rect;

    public Bird() {
        // initialize a minimum rectangle of bird
        rect = new Rectangle(img.getWidth(), img.getHeight());
    }

    /**
     * Make the bird fly by changing its y-coordinate.
     */
    public void fly() {
        // the range of bird's y-coordinate
        int lowestY = 20, highestY = 450;
        if (isUpward) {
            y -= speed;
            if (y < lowestY) {
                y = lowestY;
            }
        }
        if (!isUpward) {
            y += speed;
            if (y > highestY) {
                y = highestY;
            }
        }
        rectMove();
    }

    /**
     * Change the bird's flying direction.
     * @param direction int, 1 means fly upward, other value means fly downward
     */
    public void changeDirection(int direction) {
        switch (direction) {
            case 1:
                isUpward = true;
                break;
            default:
                isUpward = false;
        }
    }

    /**
     * Make the rectangle move along with the bird.
     */
    public void rectMove() {
        rect.x = x;
        rect.y = y;
    }
}
