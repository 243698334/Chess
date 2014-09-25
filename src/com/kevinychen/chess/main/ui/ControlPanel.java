package com.kevinychen.chess.main.ui;

import com.kevinychen.chess.main.util.GameModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class ControlPanel extends JPanel implements Observer {

    private GameModel gameModel;

    private JButton undoButton;
    private JButton saveButton;
    private JButton loadButton;

    public ControlPanel(GameModel gameModel) {
        this.gameModel = gameModel;
        initialize();
        gameModel.addObserver(this);
    }

    private void initialize() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setLayout(new GridLayout(0, 1));

        undoButton = new JButton("Request Undo");
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameModel.onUndoRequest();
            }
        });
        saveButton = new JButton("Save Game");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        loadButton = new JButton("Load Game");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        this.add(undoButton);
        this.add(saveButton);
        this.add(loadButton);
        this.setPreferredSize(new Dimension(300, 200));
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public static void main(String[] args) throws InterruptedException {
        JFrame testFrame = new JFrame("ControlPanel Test");
        ControlPanel testControlPanel = new ControlPanel(new GameModel());
        testFrame.add(testControlPanel);
        testFrame.pack();
        testFrame.setVisible(true);
    }
}
