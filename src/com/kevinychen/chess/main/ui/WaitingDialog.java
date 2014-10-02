package com.kevinychen.chess.main.ui;

import com.kevinychen.chess.main.util.Core;
import com.kevinychen.chess.main.util.GameModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WaitingDialog extends JFrame {

    private GameModel gameModel;

    private JPanel waitingPanel;
    private JProgressBar waitingProgressBar;
    private JLabel waitingLabel;

    private JPanel buttonPanel;
    private JButton cancelButton;

    public WaitingDialog(GameModel gameModel) {
        super("Please Wait...");
        this.gameModel = gameModel;
        loadWaitingInterface();
    }

    public void loadErrorInterface() {
        waitingProgressBar.setVisible(false);
        waitingLabel.setText("Connection error!");
        cancelButton.setEnabled(true);
    }

    private void loadWaitingInterface() {
        initializeWaitingPanel();
        initializeButtonPanel();

        this.setLayout(new BorderLayout());
        this.add(waitingPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.PAGE_END);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(Core.getLaunchFrame());
        this.setVisible(true);
        //this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void initializeWaitingPanel() {
        waitingLabel = new JLabel("Waiting for connection...");
        waitingProgressBar = new JProgressBar(0, 100);
        waitingProgressBar.setIndeterminate(true);
        waitingPanel = new JPanel(new GridLayout(2, 1));
        waitingPanel.add(waitingLabel);
        waitingPanel.add(waitingProgressBar);
        waitingPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        waitingPanel.setPreferredSize(new Dimension(300, 100));
    }

    private void initializeButtonPanel() {
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameModel.getGameFrame().dispose();
                Core.getLaunchFrame().setVisible(true);
            }
        });
        cancelButton.setEnabled(false);
        buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        buttonPanel.add(cancelButton, BorderLayout.EAST);
    }

}
