package view;

import common.Barrier;
import common.Bird;

import java.util.List;

public interface GameView {
    void draw(boolean isGameOver, Bird bird, List<Barrier> barriers);
}
