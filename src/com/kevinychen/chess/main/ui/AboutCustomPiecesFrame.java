package com.kevinychen.chess.main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutCustomPiecesFrame extends JFrame {

    private JPanel customPiecesPanel;
    private JLabel cannonImageLabel;
    private JLabel cannonTextArea;
    private JLabel shieldImageLabel;
    private JLabel shieldTextArea;

    private JPanel buttonPanel;
    private JButton okButton;

    public AboutCustomPiecesFrame() {
        super("About Custom Pieces");
        loadInterface();
    }

    private void loadInterface() {
        initializeCustomPiecesPanel();
        initializeButtonPanel();

        this.setLayout(new BorderLayout());

        this.add(customPiecesPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.PAGE_END);
        this.setPreferredSize(new Dimension(300, 300));
        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    private void initializeCustomPiecesPanel() {
        Image cannonImage = new ImageIcon(getClass().getResource("/pieces/black_cannon.png")).getImage();
        cannonImage = cannonImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        Image shieldImage = new ImageIcon(getClass().getResource("/pieces/white_shield.png")).getImage();
        shieldImage = shieldImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

        cannonImageLabel = new JLabel(new ImageIcon(cannonImage));
        cannonImageLabel.setPreferredSize(new Dimension(100, 100));
        cannonTextArea = new JLabel();
        cannonTextArea.setBackground(new Color(0, 0, 0, 0));
        cannonTextArea.setPreferredSize(new Dimension(200, 100));
        cannonTextArea.setText("<html><tag></tag>A Cannon can only capture an enemy piece, except for Shield, by hopping over a hurdle piece. It moves the same way as a Rook. <br> </html>");

        shieldImageLabel = new JLabel(new ImageIcon(shieldImage));
        shieldImageLabel.setPreferredSize(new Dimension(100, 100));
        shieldTextArea = new JLabel();
        shieldTextArea.setBackground(new Color(0, 0, 0, 0));
        shieldTextArea.setPreferredSize(new Dimension(200, 100));
        shieldTextArea.setText("<html><tag></tag>A Shield moves like a King, namely one step for all directions. It cannot capture any piece. <br> </html>");

        customPiecesPanel = new JPanel(new SpringLayout());
        customPiecesPanel.add(cannonImageLabel);
        customPiecesPanel.add(cannonTextArea);
        customPiecesPanel.add(shieldImageLabel);
        customPiecesPanel.add(shieldTextArea);
        SpringUtilities.makeCompactGrid(customPiecesPanel, 2, 2, 0, 0, 10, 10);
    }

    private void initializeButtonPanel() {
        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(okButton, BorderLayout.EAST);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
    }
}
