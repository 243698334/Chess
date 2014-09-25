package com.kevinychen.chess.main.ui;

import com.kevinychen.chess.main.util.GameModel;
import com.kevinychen.chess.main.util.Move;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class MoveHistoryPanel extends JPanel implements Observer {

    private GameModel gameModel;
    private JTextArea moveHistoryTextArea;

    public MoveHistoryPanel(GameModel gameModel) {
        super(new GridLayout());
        this.gameModel = gameModel;
        initialize();
    }

    public void printMove(Move move) {
        String newMoveEntry = "";
        newMoveEntry.concat(move.getPiece().getColor().toString() + " ");
        newMoveEntry.concat(move.getPiece().getType().toString() + ": ");
        newMoveEntry.concat(move.getOriginFile() + move.getOriginRank() + " - ");
        newMoveEntry.concat(move.getDestinationFile() + move.getDestinationRank() + " ");
        if (move.getCapturedPiece() != null) {
            newMoveEntry.concat("captures ");
            newMoveEntry.concat(move.getCapturedPiece().getType().toString());
        }
        newMoveEntry.concat("\n");
        moveHistoryTextArea.append(newMoveEntry);
        moveHistoryTextArea.update(moveHistoryTextArea.getGraphics());
        moveHistoryTextArea.repaint();
        this.revalidate();
    }

    public void deleteLastMove() {
        String moveHistoryContent = moveHistoryTextArea.getText();
        moveHistoryContent = moveHistoryContent.substring(0, moveHistoryContent.lastIndexOf('\n'));
        moveHistoryTextArea.setText(moveHistoryContent);
        moveHistoryTextArea.update(moveHistoryTextArea.getGraphics());
        moveHistoryTextArea.revalidate();
    }

    private void initialize() {
        moveHistoryTextArea = new JTextArea("Game start!\n");
        moveHistoryTextArea.setBackground(Color.GRAY);
        moveHistoryTextArea.setPreferredSize(new Dimension(300, 400));
        this.setBorder(BorderFactory.createTitledBorder("Move History"));
        this.add(moveHistoryTextArea);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public static void main(String[] args) {
        JFrame testFrame = new JFrame("MoveHistoryPanel Test");
        MoveHistoryPanel testMoveHistoryPanel = new MoveHistoryPanel(new GameModel());
        testFrame.add(testMoveHistoryPanel);
        testFrame.pack();
        testFrame.setVisible(true);
    }
}
