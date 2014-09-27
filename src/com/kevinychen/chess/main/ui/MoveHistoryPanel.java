package com.kevinychen.chess.main.ui;

import com.kevinychen.chess.main.pieces.Pawn;
import com.kevinychen.chess.main.pieces.Piece;
import com.kevinychen.chess.main.util.GameModel;
import com.kevinychen.chess.main.util.Move;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class MoveHistoryPanel extends JPanel implements Observer {

    private GameModel gameModel;
    private JScrollPane moveHistoryScrollPane;
    private JTextArea moveHistoryTextArea;
    private String moveHistoryContent;

    public MoveHistoryPanel(GameModel gameModel) {
        this.gameModel = gameModel;
        initialize();
    }

    public void printMove(Move move) {
        String newMoveEntry = "";
        newMoveEntry += move.getPiece().getColor().toString() + " ";
        newMoveEntry += move.getPiece().getType().toString() + ": ";
        newMoveEntry += move.getOriginFile();
        newMoveEntry += move.getOriginRank() + " - ";
        newMoveEntry += move.getDestinationFile();
        newMoveEntry += move.getDestinationRank() + " ";
        if (move.getCapturedPiece() != null) {
            newMoveEntry += "captures ";
            newMoveEntry += move.getCapturedPiece().getType().toString();
        }
        newMoveEntry += "\n";

        moveHistoryContent += newMoveEntry;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                moveHistoryTextArea.setText(moveHistoryContent);
            }
        });
    }

    public void deleteLastMove() {
        moveHistoryContent = moveHistoryContent.substring(0, moveHistoryContent.lastIndexOf('\n'));
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                moveHistoryTextArea.setText(moveHistoryContent);
            }
        });
    }

    private void initialize() {
        moveHistoryContent = new String("Game start!\n");
        moveHistoryTextArea = new JTextArea(moveHistoryContent);
        moveHistoryTextArea.setBackground(Color.GRAY);
        moveHistoryScrollPane = new JScrollPane(moveHistoryTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        moveHistoryScrollPane.setBorder(BorderFactory.createTitledBorder("Move History"));
        moveHistoryScrollPane.setViewportView(moveHistoryTextArea);
        moveHistoryScrollPane.setPreferredSize(new Dimension(300, 400));
        //this.setPreferredSize(new Dimension(300, 400));
        this.add(moveHistoryScrollPane);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public static void main(String[] args) {
        JFrame testFrame = new JFrame("MoveHistoryPanel Test");
        //testFrame.setPreferredSize(new Dimension(300, 400));
        MoveHistoryPanel testMoveHistoryPanel = new MoveHistoryPanel(new GameModel());
        testFrame.add(testMoveHistoryPanel);
        for (int i = 0; i < 30; i++) {
            testMoveHistoryPanel.printMove(new Move(new Pawn(Piece.Color.WHITE), new Pawn(Piece.Color.BLACK), 'a', i, 'a', 5));
        }
        testFrame.pack();
        testFrame.setVisible(true);
    }
}
