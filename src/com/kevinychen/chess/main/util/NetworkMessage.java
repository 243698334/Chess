package com.kevinychen.chess.main.util;

import com.kevinychen.chess.main.pieces.Piece;
import com.kevinychen.chess.main.util.Preferences.*;

import java.io.Serializable;

public class NetworkMessage implements Serializable {

    public enum Type {
        HANDSHAKE, MOVE, UNDO,
        MOVE_RESPONSE, UNDO_RESPONSE,
        DISCONNECT
    }

    private Type type;

    // handshake
    private String playerName;
    private Piece.Color color;
    private NetworkMode networkMode;
    private TimerMode timerMode;
    private int timeLimit;
    private boolean usingCustomPieces;
    private boolean boardReversed;

    // move
    private char originFile;
    private int originRank;
    private char destinationFile;
    private int destinationRank;
    private boolean moveValid;

    // undo
    private boolean undoAccepted;

    public NetworkMessage(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Piece.Color getColor() {
        return color;
    }

    public void setColor(Piece.Color color) {
        this.color = color;
    }

    public NetworkMode getNetworkMode() {
        return networkMode;
    }

    public void setNetworkMode(NetworkMode networkMode) {
        this.networkMode = networkMode;
    }

    public TimerMode getTimerMode() {
        return timerMode;
    }

    public void setTimerMode(TimerMode timerMode) {
        this.timerMode = timerMode;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public boolean isUsingCustomPieces() {
        return usingCustomPieces;
    }

    public void setUsingCustomPieces(boolean usingCustomPieces) {
        this.usingCustomPieces = usingCustomPieces;
    }

    public boolean isBoardReversed() {
        return boardReversed;
    }

    public void setBoardReversed(boolean boardReversed) {
        this.boardReversed = boardReversed;
    }

    public char getOriginFile() {
        return originFile;
    }

    public int getOriginRank() {
        return originRank;
    }

    public char getDestinationFile() {
        return destinationFile;
    }

    public int getDestinationRank() {
        return destinationRank;
    }

    public void setMove(char originFile, int originRank, char destinationFile, int destinationRank) {
        this.originFile = originFile;
        this.originRank = originRank;
        this.destinationFile = destinationFile;
        this.destinationRank = destinationRank;
    }

    public boolean isUndoAccepted() {
        return undoAccepted;
    }

    public void setUndoAccepted(boolean undoAccepted) {
        this.undoAccepted = undoAccepted;
    }

    public boolean isMoveValid() {
        return moveValid;
    }

    public void setMoveValid(boolean moveValid) {
        this.moveValid = moveValid;
    }

}
