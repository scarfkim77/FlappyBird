package view;

import static common.Constant.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BackGround {
    private BufferedImage bgimg;

    public BackGround() {
        bgimg = GameUtil.loadBufferedImage(BG_IMG_PATH);
    }

    public void draw(Graphics g) {
        // use color to fill the bg
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        g.setColor(Color.black);

        int width = bgimg.getWidth();
        int height = bgimg.getHeight();
        int count = FRAME_WIDTH / width + 1;
        for (int i = 0; i < count; i++) {
            g.drawImage(bgimg, width * i, FRAME_HEIGHT - height, null);
        }
    }

}
