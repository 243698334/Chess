package com.kevinychen.chess.main.util;

import com.kevinychen.chess.main.board.Board;
import com.kevinychen.chess.main.pieces.Piece;
import com.kevinychen.chess.main.ui.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class GameModel extends Observable {

    private BoardPanel boardPanel;
    private TimerPanel timerPanel;
    private ControlPanel controlPanel;
    private MoveHistoryPanel moveHistoryPanel;

    private Timer whiteTimer;
    private Timer blackTimer;

    private boolean reverseBoard = false;

    public GameModel() {
        initializePanels();
        initializeTimers();
    }

    public void onMoveRequest(char originFile, int originRank, char destinationFile, int destinationRank) {
        Move move = new Move(originFile, originRank, destinationFile, destinationRank);
        if (MoveValidator.validateMove(move)) {
            MoveLogger.addMove(move);
            Board.executeMove(move);
            if (MoveValidator.isCheckMove(move)) {
                if (MoveValidator.isCheckMate(move)) {
                    // checkmate
                }
                // check
            }
            boardPanel.executeMove(move);
            moveHistoryPanel.printMove(move);
            switchTimer(move);
        } else {
            //return false;
        }
    }

    public void onUndoRequest() {
        Move lastMove = MoveLogger.undoLastMove();
        if (lastMove != null) {
            if (MoveValidator.validateUndo(lastMove)) {
                boardPanel.executeUndo(lastMove);
                moveHistoryPanel.deleteLastMove();
                Board.executeUndo(lastMove);
                switchTimer(lastMove);
            }
        }
        // TODO timer update
    }

    public Piece onQueryRequest(char file, int rank) {
        return Board.getSquare(file, rank).getCurrentPiece();
    }

    private void initializeTimers() {
        whiteTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerPanel.whiteTimerTikTok();
            }
        });
        blackTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerPanel.blackTimerTikTok();
            }
        });
    }

    private void switchTimer(Move move) {
        if (move.getPiece().getColor().equals(Piece.Color.BLACK)) {
            whiteTimer.start();
            blackTimer.stop();
        } else {
            blackTimer.start();
            whiteTimer.stop();
        }
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public TimerPanel getTimerPanel() {
        return timerPanel;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public MoveHistoryPanel getMoveHistoryPanel() {
        return moveHistoryPanel;
    }

    public boolean isReverseBoard() {
        return reverseBoard;
    }

    private void initializePanels() {
        boardPanel = new BoardPanel(this);
        timerPanel = new TimerPanel(this);
        controlPanel = new ControlPanel(this);
        moveHistoryPanel = new MoveHistoryPanel(this);
    }



    public static void main(String[] args) {
        GameModel gameModel = new GameModel();
        JFrame testFrame = new JFrame("GameModel Test");
        testFrame.pack();
        testFrame.setVisible(true);
    }



}
