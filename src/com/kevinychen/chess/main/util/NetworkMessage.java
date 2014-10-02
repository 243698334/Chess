package com.kevinychen.chess.main.util;

import com.kevinychen.chess.main.pieces.Piece;
import com.kevinychen.chess.main.util.Preferences.*;
import org.json.JSONObject;

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

    public NetworkMessage(String networkMessageString) {
        parseNetworkMessage(networkMessageString);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    @Override
    public String toString() {
        JSONObject networkMessageJSON = new JSONObject();
        networkMessageJSON.put("type", type.toString());
        switch (type) {
            case HANDSHAKE:
                networkMessageJSON.put("player_name", playerName);
                networkMessageJSON.put("timer_mode", timerMode.toString());
                if (timerMode.equals(TimerMode.COUNTDOWN)) {
                    networkMessageJSON.put("time_limit", timeLimit);
                }
                networkMessageJSON.put("using_custom_pieces", usingCustomPieces);
                break;
            case MOVE:
                networkMessageJSON.put("origin_file", originFile);
                networkMessageJSON.put("origin_rank", originRank);
                networkMessageJSON.put("destination_file", destinationFile);
                networkMessageJSON.put("destination_rank", destinationRank);
                break;
            case UNDO:

                break;
            case MOVE_RESPONSE:
                networkMessageJSON.put("move_valid", moveValid);
                break;
            case UNDO_RESPONSE:
                networkMessageJSON.put("undo_accepted", undoAccepted);
                break;
            case DISCONNECT:
                break;
        }
        return networkMessageJSON.toString();
    }

    private void parseNetworkMessage(String networkMessageString) {
        JSONObject networkMessageJSON = new JSONObject(networkMessageString);
        this.type = Type.valueOf(networkMessageJSON.getString("type"));
        switch (type) {
            case HANDSHAKE:
                playerName = networkMessageJSON.getString("player_name");
                timerMode = TimerMode.valueOf(networkMessageJSON.getString("timer_mode"));
                if (timerMode.equals(TimerMode.COUNTDOWN)) {
                    timeLimit = networkMessageJSON.getInt("time_limit");
                }
                usingCustomPieces = networkMessageJSON.getBoolean("using_custom_pieces");
                break;
            case MOVE:
                originFile = (char) networkMessageJSON.getInt("origin_file");
                originRank = networkMessageJSON.getInt("origin_rank");
                destinationFile = (char) networkMessageJSON.getInt("destination_file");
                destinationRank = networkMessageJSON.getInt("destination_rank");
                break;
            case UNDO:
                break;
            case MOVE_RESPONSE:
                moveValid = networkMessageJSON.getBoolean("move_valid");
                break;
            case UNDO_RESPONSE:
                break;
            case DISCONNECT:
                break;
        }
    }

}
