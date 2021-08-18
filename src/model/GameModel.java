package model;

import common.Barrier;
import common.Bird;

import java.util.List;

public interface GameModel {
    void run(int direction);
    boolean isGameOver();
    Bird getBird();
    List<Barrier> getBarriers();
}
