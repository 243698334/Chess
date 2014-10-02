package com.kevinychen.chess.main.ui;

import com.kevinychen.chess.main.util.Core;
import com.kevinychen.chess.main.util.Preferences;
import com.kevinychen.chess.main.util.Preferences.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreferencesFrame extends JFrame {

    private JPanel preferencesPanel;

    private JPanel bannerPanel;
    private JPanel gameModePanel;
    private JRadioButton onlineRadioButton;
    private JRadioButton offlineRadioButton;

    private JPanel settingsPanel;

    private JPanel gameSettingsPanel;
    private JPanel gameSettingsSubPanel;
    private JPanel customPiecesPanel;
    private JCheckBox customPiecesCheckBox;
    private JButton aboutCustomPiecesButton;
    private JPanel reverseBoardPanel;
    private JCheckBox reverseBoardCheckBox;
    private JPanel timerSettingsPanel;
    private JLabel timerModeLabel;
    private JPanel timerModeRadioButtonsPanel;
    private JRadioButton countdownRadioButton;
    private JRadioButton stopwatchRadioButton;
    private JPanel timeLimitPanel;
    private JFormattedTextField timeLimitFormattedTextField;
    private JLabel timeLimitUnitLabel;

    private JPanel networkSettingsPanel;
    private JPanel networkSettingsSubPanel;
    private JPanel networkModePanel;
    private JRadioButton hostGameRadioButton;
    private JRadioButton joinGameRadioButton;
    private JPanel playerNamePanel;
    private JLabel playerNameLabel;
    private JTextField playerNameTextField;
    private JPanel connectionPanel;
    private JPanel ipAndPortPanel;
    private JLabel hostIPLabel;
    private JTextField hostIPTextField;
    private JLabel hostPortLabel;
    private JFormattedTextField hostPortFormattedTextField;

    private JPanel buttonsPanel;
    private JPanel buttonsSubPanel;
    private JButton okButton;
    private JButton cancelButton;

    public PreferencesFrame() {
        super("Preferences");
        loadInterface();
        loadPreferences();
    }

    private void loadPreferences() {
        Preferences preferences = Core.getPreferences();
        if (!preferences.isPreferencesComplete()) {
            return;
        }
        switch (preferences.getGameMode()) {
            case ONLINE:
                onlineRadioButton.setSelected(true);
                setNetworkSettingsEnabled(true);
                switch (preferences.getNetworkMode()) {
                    case HOST:
                        hostGameRadioButton.setSelected(true);
                        hostIPTextField.setEnabled(false);
                        hostIPTextField.setText(Core.getLocalIPAddress());
                        break;
                    case CLIENT:
                        joinGameRadioButton.setSelected(true);
                        hostIPTextField.setEnabled(true);
                        hostIPTextField.setText(preferences.getHostIP());
                        break;
                }
                break;
            case OFFLINE:
                setNetworkSettingsEnabled(true);
                offlineRadioButton.setSelected(true);
                break;
        }
        switch (preferences.getTimerMode()) {
            case COUNTDOWN:
                countdownRadioButton.setSelected(true);
                timeLimitPanel.setVisible(true);
                timeLimitFormattedTextField.setText(Integer.toString(preferences.getTimeLimit()));
                break;
            case STOPWATCH:
                stopwatchRadioButton.setSelected(true);
                timeLimitPanel.setVisible(false);
                break;
        }
        customPiecesCheckBox.setSelected(preferences.isUsingCustomPieces());
        reverseBoardCheckBox.setSelected(preferences.isBoardReversed());

        if (Core.isInGame()) {
            setNetworkSettingsEnabled(false);
            customPiecesCheckBox.setEnabled(false);
        }
    }

    private void loadInterface() {
        initializeBannerPanel();
        initializeSettingsPanel();
        initializeButtonsPanel();

        preferencesPanel = new JPanel(new BorderLayout());
        preferencesPanel.add(bannerPanel, BorderLayout.PAGE_START);
        preferencesPanel.add(settingsPanel, BorderLayout.CENTER);
        preferencesPanel.add(buttonsPanel, BorderLayout.PAGE_END);
        preferencesPanel.setPreferredSize(new Dimension(600, 400));

        this.add(preferencesPanel);
        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(Core.getLaunchFrame());
    }

    private void initializeBannerPanel() {
        onlineRadioButton = new JRadioButton("Online");
        onlineRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGameSettingsEnabled(!joinGameRadioButton.isSelected());
                setNetworkSettingsEnabled(true);
            }
        });
        offlineRadioButton = new JRadioButton("Offline");
        offlineRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGameSettingsEnabled(true);
                setNetworkSettingsEnabled(false);
            }
        });
        ButtonGroup gameModeButtonGroup = new ButtonGroup();
        gameModeButtonGroup.add(onlineRadioButton);
        gameModeButtonGroup.add(offlineRadioButton);
        gameModePanel = new JPanel();
        gameModePanel.setBackground(Color.LIGHT_GRAY);
        gameModePanel.add(onlineRadioButton);
        gameModePanel.add(offlineRadioButton);

        bannerPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon(getClass().getResource("/preferences_banner.png")).getImage(), 0, 0, null);
            }
        };
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = gridBagConstraints.weighty = 1;
        gridBagConstraints.anchor = GridBagConstraints.SOUTHWEST;
        bannerPanel.setPreferredSize(new Dimension(600, 120));
        bannerPanel.setBackground(Color.LIGHT_GRAY);
        bannerPanel.add(gameModePanel, gridBagConstraints);
    }

    private void initializeSettingsPanel() {
        initializeGameSettingsPanel();
        initializeNetworkSettingsPanel();

        settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        settingsPanel.add(gameSettingsPanel);
        settingsPanel.add(networkSettingsPanel);
    }

    private void initializeGameSettingsPanel() {
        // custom pieces
        customPiecesCheckBox = new JCheckBox("Use Custom Piece");
        aboutCustomPiecesButton = new JButton("More about Custom Piece...");
        aboutCustomPiecesButton.setForeground(Color.BLUE);
        aboutCustomPiecesButton.setBorder(BorderFactory.createEmptyBorder());
        aboutCustomPiecesButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        aboutCustomPiecesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AboutCustomPiecesFrame();
            }
        });
        customPiecesPanel = new JPanel(new BorderLayout());
        customPiecesPanel.add(customPiecesCheckBox, BorderLayout.WEST);
        customPiecesPanel.add(aboutCustomPiecesButton, BorderLayout.CENTER);

        // reverse board
        reverseBoardCheckBox = new JCheckBox("Reverse Board");
        reverseBoardPanel = new JPanel(new BorderLayout());
        reverseBoardPanel.add(reverseBoardCheckBox, BorderLayout.WEST);

        // timer settings
        timerModeLabel = new JLabel("Timer Mode: ");
        countdownRadioButton = new JRadioButton("Countdown");
        countdownRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLimitPanel.setVisible(true);
            }
        });
        stopwatchRadioButton = new JRadioButton("Stopwatch");
        stopwatchRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLimitPanel.setVisible(false);
            }
        });
        ButtonGroup timerModeButtonGroup = new ButtonGroup();
        timerModeButtonGroup.add(countdownRadioButton);
        timerModeButtonGroup.add(stopwatchRadioButton);
        timeLimitFormattedTextField = new JFormattedTextField(5);
        timeLimitFormattedTextField.setValue(new Integer(20));
        timeLimitUnitLabel = new JLabel("min");
        timeLimitPanel = new JPanel(new BorderLayout());
        timeLimitPanel.add(timeLimitFormattedTextField, BorderLayout.WEST);
        timeLimitPanel.add(timeLimitUnitLabel);
        timeLimitPanel.setVisible(false);
        timerModeRadioButtonsPanel = new JPanel(new GridLayout(1, 3));
        timerModeRadioButtonsPanel.add(stopwatchRadioButton);
        timerModeRadioButtonsPanel.add(countdownRadioButton);
        timerModeRadioButtonsPanel.add(timeLimitPanel);


        timerSettingsPanel = new JPanel(new SpringLayout());
        timerModeLabel.setLabelFor(timerModeRadioButtonsPanel);
        timerSettingsPanel.add(timerModeLabel);
        timerSettingsPanel.add(timerModeRadioButtonsPanel);
        SpringUtilities.makeCompactGrid(timerSettingsPanel, 1, 2, 8, 0, 0, 0);

        gameSettingsSubPanel = new JPanel(new BorderLayout());
        gameSettingsSubPanel.add(customPiecesPanel, BorderLayout.PAGE_START);
        gameSettingsSubPanel.add(reverseBoardPanel, BorderLayout.CENTER);
        gameSettingsSubPanel.add(timerSettingsPanel, BorderLayout.PAGE_END);
        gameSettingsPanel = new JPanel();
        gameSettingsPanel.add(gameSettingsSubPanel);
        gameSettingsPanel.setBorder(BorderFactory.createTitledBorder("Game Settings"));

    }

    private void initializeNetworkSettingsPanel() {
        // network mode
        joinGameRadioButton = new JRadioButton("Join");
        joinGameRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reverseBoardCheckBox.setSelected(true);
                setGameSettingsEnabled(false);
                hostIPTextField.setEnabled(true);
                onlineRadioButton.setSelected(true);
            }
        });
        hostGameRadioButton = new JRadioButton("Host");
        hostGameRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reverseBoardCheckBox.setSelected(false);
                setGameSettingsEnabled(true);
                hostIPTextField.setText(Core.getLocalIPAddress());
                hostIPTextField.setEnabled(false);
                onlineRadioButton.setSelected(true);
            }
        });
        ButtonGroup networkModeButtonGroup = new ButtonGroup();
        networkModeButtonGroup.add(joinGameRadioButton);
        networkModeButtonGroup.add(hostGameRadioButton);
        playerNameLabel = new JLabel("Name:");
        playerNameTextField = new JTextField(6);
        playerNameLabel.setLabelFor(playerNameTextField);
        playerNamePanel = new JPanel();
        playerNamePanel.add(playerNameLabel);
        playerNamePanel.add(playerNameTextField);
        networkModePanel = new JPanel();
        networkModePanel.add(joinGameRadioButton);
        networkModePanel.add(hostGameRadioButton);
        networkModePanel.add(playerNamePanel);

        // connection panel
        hostIPLabel = new JLabel("Host IP:");
        hostIPTextField = new JTextField(10);
        hostPortLabel = new JLabel(":");
        hostPortFormattedTextField = new JFormattedTextField("9332");
        connectionPanel = new JPanel(new SpringLayout());

        ipAndPortPanel = new JPanel();
        ipAndPortPanel.add(hostIPTextField);
        ipAndPortPanel.add(hostPortLabel);
        ipAndPortPanel.add(hostPortFormattedTextField);
        hostIPLabel.setLabelFor(ipAndPortPanel);

        connectionPanel.add(hostIPLabel);
        connectionPanel.add(ipAndPortPanel);
        SpringUtilities.makeCompactGrid(connectionPanel, 1, 2, 8, 0, 0, 0);

        networkSettingsSubPanel = new JPanel(new BorderLayout());
        networkSettingsSubPanel.add(networkModePanel, BorderLayout.PAGE_START);
        networkSettingsSubPanel.add(connectionPanel, BorderLayout.CENTER);
        networkSettingsPanel = new JPanel();
        networkSettingsPanel.add(networkSettingsSubPanel);
        networkSettingsPanel.setBorder(BorderFactory.createTitledBorder("Network Settings"));
    }

    private void initializeButtonsPanel() {
        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (submitPreference()) {
                    if (!Core.isInGame()) {
                        Core.startGame();
                    }
                    dispose();
                } else {
                    showIncompleteDialog();
                }
            }
        });
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Core.getLaunchFrame().setVisible(true);
                dispose();
            }
        });
        buttonsSubPanel = new JPanel();
        buttonsSubPanel.add(cancelButton);
        buttonsSubPanel.add(okButton);
        buttonsPanel = new JPanel(new BorderLayout());
        buttonsPanel.add(buttonsSubPanel, BorderLayout.EAST);
    }

    private void setGameSettingsEnabled(boolean b) {
        customPiecesCheckBox.setEnabled(b);
        reverseBoardCheckBox.setEnabled(b);
        timerModeLabel.setEnabled(b);
        stopwatchRadioButton.setEnabled(b);
        countdownRadioButton.setEnabled(b);
        timeLimitFormattedTextField.setEnabled(b);
        timeLimitUnitLabel.setEnabled(b);
        timeLimitPanel.setEnabled(b);
        gameSettingsPanel.setEnabled(b);
    }

    private void setNetworkSettingsEnabled(boolean b) {
        joinGameRadioButton.setEnabled(b);
        hostGameRadioButton.setEnabled(b);
        playerNameLabel.setEnabled(b);
        playerNameTextField.setEnabled(b);
        hostIPLabel.setEnabled(b);
        hostIPTextField.setEnabled(b);
        hostIPTextField.setEditable(b);
        hostPortLabel.setEnabled(b);
        hostPortFormattedTextField.setEnabled(b);
        networkSettingsPanel.setEnabled(b);
    }

    private boolean submitPreference() {
        Preferences preferences = Core.getPreferences();
        if (onlineRadioButton.isSelected()) {
            preferences.setGameMode(GameMode.ONLINE);
            if (joinGameRadioButton.isSelected()) {
                preferences.setNetworkMode(NetworkMode.CLIENT);
            }
            if (hostGameRadioButton.isSelected()) {
                preferences.setNetworkMode(NetworkMode.HOST);
            }
            preferences.setHostIP(hostIPTextField.getText());
            preferences.setPort(Integer.parseInt(hostPortFormattedTextField.getText()));
            preferences.setPlayerName(playerNameTextField.getText());
        }
        if (offlineRadioButton.isSelected()) {
            preferences.setGameMode(GameMode.OFFLINE);
        }
        preferences.setUsingCustomPieces(customPiecesCheckBox.isSelected());
        preferences.setBoardReversed(reverseBoardCheckBox.isSelected());
        if (stopwatchRadioButton.isSelected()) {
            preferences.setTimerMode(TimerMode.STOPWATCH);
        }
        if (countdownRadioButton.isSelected()) {
            preferences.setTimerMode(TimerMode.COUNTDOWN);
            preferences.setTimeLimit((Integer) timeLimitFormattedTextField.getValue());
        }
        return preferences.isPreferencesComplete();
    }

    private void showIncompleteDialog() {
        JOptionPane.showMessageDialog(this, "Please set all necessary preferences. ", "Unfinished Preferences", JOptionPane.WARNING_MESSAGE);
    }

}