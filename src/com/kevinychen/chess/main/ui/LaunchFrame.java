package com.kevinychen.chess.main.ui;

import com.kevinychen.chess.main.util.Core;
import com.kevinychen.chess.main.util.Preferences;
import com.kevinychen.chess.main.util.SaverLoader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchFrame extends JFrame {

    private JPanel bannerPanel;
    private JLabel bannerLabel;

    private JPanel buttonsPanel;
    private JPanel newGameButtonPanel;
    private JPanel loadGameButtonPanel;
    private JButton newGameButton;
    private JButton loadGameButton;
    private JFileChooser loadGameFileChooser;

    public LaunchFrame() {
        super("Chess.242");
        loadInterface();
    }

    private void loadInterface() {
        initializeBannerPanel();
        initializeButtonsPanel();

        this.setLayout(new BorderLayout());
        this.add(bannerPanel, BorderLayout.NORTH);
        this.add(buttonsPanel, BorderLayout.SOUTH);

        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    private void initializeBannerPanel() {
        bannerLabel = new JLabel();
        bannerLabel.setIcon(new ImageIcon(getClass().getResource("/launch_banner.png")));
        bannerPanel = new JPanel();
        bannerPanel.add(bannerLabel);
        bannerPanel.setPreferredSize(new Dimension(600, 250));
        bannerPanel.setBackground(Color.LIGHT_GRAY);
    }

    private void initializeButtonsPanel() {
        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PreferencesFrame preferencesFrame = Core.getPreferencesFrame();
                preferencesFrame = new PreferencesFrame();
                setVisible(false);
            }
        });
        newGameButtonPanel = new JPanel(new GridLayout(1, 1));
        newGameButtonPanel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 25));
        newGameButtonPanel.add(newGameButton);
        loadGameFileChooser = new JFileChooser("Load Saved Game");
        //loadGameFileChooser.setCurrentDirectory(new File("saved games/"));
        loadGameFileChooser.setFileFilter(new FileNameExtensionFilter("Saved Game", "chessgame"));
        loadGameButton = new JButton("Load Game");
        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadGameFileChooser.showOpenDialog(Core.getLaunchFrame());
                SaverLoader.loadGame(loadGameFileChooser.getSelectedFile());
            }
        });
        loadGameButtonPanel = new JPanel(new GridLayout(1, 1));
        loadGameButtonPanel.setBorder(BorderFactory.createEmptyBorder(40, 25, 40, 50));
        loadGameButtonPanel.add(loadGameButton);


        buttonsPanel = new JPanel(new GridLayout(1, 2));
        buttonsPanel.setPreferredSize(new Dimension(600, 150));
        buttonsPanel.add(newGameButtonPanel);
        buttonsPanel.add(loadGameButtonPanel);
    }

}
