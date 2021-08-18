package common;

import java.awt.*;

public class Constant {
    public static final int FRAME_WIDTH = 600;
    public static final int FRAME_HEIGHT = 500;
    public static final String FRAME_TITLE = "Flappy Bird";
    public static final int FRAME_X = 200;
    public static final int FRAME_Y = 200;

    // bg image
    public static final String BG_IMG_PATH = "img/bird_bg.png";

    // bg color
    public static final Color BG_COLOR = new Color(0X4B4CF);

    // bird's images
    public static final String BIRD_IMG = "img/bird_normal.png";

    // barrier's images
    public static final String[] BARRIER_IMG = {
            "img/barrier.png", "img/barrier_up.png", "img/barrier_down.png"};
}
