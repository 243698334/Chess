package com.kevinychen.chess.main.ui;

import com.kevinychen.chess.main.util.Core;
import com.kevinychen.chess.main.util.GameModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import static java.lang.System.exit;

public class GameFrame extends JFrame implements Observer{

    private GameModel gameModel;

    private JMenuBar menuBar;
    private JMenu gameMenu;
    private JMenuItem newGameMenuItem;
    private JMenuItem saveGameMenuItem;
    private JMenuItem preferencesMenuItem;
    private JMenuItem exitMenuItem;

    private JMenu helpMenu;
    private JMenuItem customPiecesMenuItem;
    private JMenuItem aboutMenuItem;

    private JPanel boardPanel;
    private JPanel timerPanel;
    private JPanel controlPanel;
    private JPanel moveHistoryPanel;

    public GameFrame(GameModel gameModel) {
        this.boardPanel = gameModel.getBoardPanel();
        this.timerPanel = gameModel.getTimerPanel();
        this.controlPanel = gameModel.getControlPanel();
        this.moveHistoryPanel = gameModel.getMoveHistoryPanel();
        loadInterface();
        gameModel.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public void showCheckDialog() {
        JOptionPane.showMessageDialog(this, "That's a Check!", "Check", JOptionPane.WARNING_MESSAGE);
    }

    private void loadInterface() {
        initializeMenuBar();
        initializePanels();
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(Core.getLaunchFrame());
        this.setVisible(true);
    }

    private void initializeMenuBar() {
        // game menu
        newGameMenuItem = new JMenuItem("New Game");
        newGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        saveGameMenuItem = new JMenuItem("Save Game");
        saveGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        preferencesMenuItem = new JMenuItem("Preferences");
        preferencesMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userChoice = JOptionPane.showConfirmDialog(getContentPane(),
                        "Do you really want to exit?", "Confirm exit", JOptionPane.YES_NO_OPTION);
                if (userChoice == JOptionPane.YES_OPTION) {
                    exit(0);
                }
            }
        });

        gameMenu = new JMenu("Game");
        gameMenu.add(newGameMenuItem);
        gameMenu.add(saveGameMenuItem);
        gameMenu.add(preferencesMenuItem);
        gameMenu.addSeparator();
        gameMenu.add(exitMenuItem);

        // help menu
        customPiecesMenuItem = new JMenuItem("Custom pieces...");
        customPiecesMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        helpMenu = new JMenu("Help");
        helpMenu.add(customPiecesMenuItem);
        helpMenu.add(aboutMenuItem);
        helpMenu.addSeparator();

        // menu bar
        menuBar = new JMenuBar();
        menuBar.add(gameMenu);
        menuBar.add(helpMenu);
        menuBar.setVisible(true);
        this.setJMenuBar(menuBar);
    }

    private void initializePanels() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(gridBagLayout);

        // BoardPanel
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 4;
        gridBagLayout.setConstraints(boardPanel, gridBagConstraints);
        this.add(boardPanel);

        // TimerPanel
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagLayout.setConstraints(timerPanel, gridBagConstraints);
        this.add(timerPanel);

        // ControlPanel
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagLayout.setConstraints(controlPanel, gridBagConstraints);
        this.add(controlPanel);

        // MoveHistoryPanel
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 2;
        gridBagLayout.setConstraints(moveHistoryPanel, gridBagConstraints);
        this.add(moveHistoryPanel);
    }

}
