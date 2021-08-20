package controller;

import common.KeyBoardHandler;
import model.GameModel;
import model.GameModelImpl;
import view.GameView;
import view.GameViewImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameControllerImpl implements ActionListener {
    private KeyBoardHandler keyInput;
    private GameModel model;
    private GameView view;

    public GameControllerImpl() {
        keyInput = new KeyBoardHandler();
        model = new GameModelImpl();
        view = new GameViewImpl(keyInput);
        new Timer(33, this).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int direction = keyInput.getInput();
        model.run(direction);
        view.draw(model.isGameOver(), model.getBird(), model.getBarriers());
    }

    public static void main(String[] args) {
        new GameControllerImpl();
    }
}
