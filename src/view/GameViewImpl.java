package view;

import common.Barrier;
import common.Bird;

import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class GameViewImpl implements GameView {
    private MainFrame frame;

    public GameViewImpl(KeyListener keyInput) {
        frame = new MainFrame();
        // end the process when window is closed
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.addKeyListener(keyInput);
    }

    @Override
    public void draw(boolean isGameOver, Bird bird, List<Barrier> barriers) {
        frame.draw(isGameOver, bird, barriers);
    }

}
