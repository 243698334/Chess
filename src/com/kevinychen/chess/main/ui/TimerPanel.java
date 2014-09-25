package com.kevinychen.chess.main.ui;

import com.kevinychen.chess.main.util.GameModel;

import javax.swing.*;
import java.awt.*;
import java.sql.Time;
import java.util.Observable;
import java.util.Observer;

public class TimerPanel extends JPanel implements Observer {

    private GameModel gameModel;
    private Time whiteTime;
    private Time blackTime;

    private JPanel displayPanel;
    private JPanel whiteTimerPanel;
    private JPanel whiteTimerDigitsPanel;
    private JLabel whiteTimerDigitsLabel;
    private JPanel whiteTimerStatusPanel;
    private JPanel blackTimerPanel;
    private JPanel blackTimerDigitsPanel;
    private JLabel blackTimerDigitsLabel;
    private JPanel blackTimerStatusPanel;

    public TimerPanel(GameModel gameModel) {
        super(new BorderLayout());
        this.gameModel = gameModel;
        whiteTime = Time.valueOf("00:00:00");
        blackTime = Time.valueOf("00:00:00");
        initialize();
        gameModel.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public void whiteTimerTikTok() {
        whiteTime.setTime(whiteTime.getTime() + 1000);
        whiteTimerDigitsLabel.setText(whiteTime.toString());
        whiteTimerStatusPanel.setVisible(true);
        blackTimerStatusPanel.setVisible(false);
    }

    public void blackTimerTikTok() {
        blackTime.setTime(blackTime.getTime() + 1000);
        blackTimerDigitsLabel.setText(blackTime.toString());
        blackTimerStatusPanel.setVisible(true);
        whiteTimerStatusPanel.setVisible(false);
    }

    public void setWhiteTimer(long time) {
        whiteTime.setTime(time);
        whiteTimerDigitsLabel.setText(whiteTime.toString());
        whiteTimerStatusPanel.setVisible(false);
    }

    public void setBlackTimer(long time) {
        blackTime.setTime(time);
        blackTimerDigitsLabel.setText(blackTime.toString());
        blackTimerStatusPanel.setVisible(false);
    }

    private void initialize() {
        whiteTimerDigitsLabel = new JLabel(whiteTime.toString());
        whiteTimerDigitsLabel.setFont(whiteTimerDigitsLabel.getFont().deriveFont(48f));
        whiteTimerDigitsPanel = new JPanel();
        whiteTimerDigitsPanel.add(whiteTimerDigitsLabel);
        whiteTimerStatusPanel = new JPanel();
        whiteTimerStatusPanel.setBackground(Color.WHITE);
        whiteTimerPanel = new JPanel(new BorderLayout());
        whiteTimerPanel.add(whiteTimerDigitsPanel, BorderLayout.LINE_START);
        whiteTimerPanel.add(whiteTimerStatusPanel, BorderLayout.CENTER);
        whiteTimerPanel.setBorder(BorderFactory.createTitledBorder("White"));

        blackTimerDigitsLabel = new JLabel(blackTime.toString());
        blackTimerDigitsLabel.setFont(blackTimerDigitsLabel.getFont().deriveFont(48f));
        blackTimerDigitsPanel = new JPanel();
        blackTimerDigitsPanel.add(blackTimerDigitsLabel);
        blackTimerStatusPanel = new JPanel();
        blackTimerStatusPanel.setBackground(Color.BLACK);
        blackTimerPanel = new JPanel(new BorderLayout());
        blackTimerPanel.add(blackTimerDigitsPanel, BorderLayout.LINE_START);
        blackTimerPanel.add(blackTimerStatusPanel, BorderLayout.CENTER);
        blackTimerPanel.setBorder(BorderFactory.createTitledBorder("Black"));

        displayPanel = new JPanel(new GridLayout(2, 1));
        displayPanel.add(whiteTimerPanel);
        displayPanel.add(blackTimerPanel);

        this.add(displayPanel, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(300, 200));
    }

    public static void main(String[] args) {
        JFrame testFrame = new JFrame("TimerPanel Test");
        //testFrame.setPreferredSize(new Dimension(300, 200));
        TimerPanel timerPanel = new TimerPanel(new GameModel());
        testFrame.add(timerPanel);
        testFrame.pack();
        testFrame.setVisible(true);
    }


}
