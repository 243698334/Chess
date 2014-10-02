package com.kevinychen.chess.main.util;

import com.kevinychen.chess.main.board.Board;
import com.kevinychen.chess.main.pieces.Piece;

public class Move {

    private Piece piece;
    private Piece capturedPiece;
    private char originFile;
    private int originRank;
    private char destinationFile;
    private int destinationRank;
    private boolean valid;
    private int timeUsed;

    public Move(char originFile, int originRank, char destinationFile, int destinationRank) {
        this.piece = Board.getSquare(originFile, originRank).getCurrentPiece();
        this.capturedPiece = Board.getSquare(destinationFile, destinationRank).getCurrentPiece();
        this.originFile = originFile;
        this.originRank = originRank;
        this.destinationFile = destinationFile;
        this.destinationRank = destinationRank;
        this.valid = false;
    }

    public Move(Piece piece, char originFile, int originRank, char destinationFile, int destinationRank) {
        this.piece = piece;
        this.capturedPiece = Board.getSquare(destinationFile, destinationRank).getCurrentPiece();
        this.originFile = originFile;
        this.originRank = originRank;
        this.destinationFile = destinationFile;
        this.destinationRank = destinationRank;
        this.valid = false;
    }

    public Move(Piece piece, Piece capturedPiece, char originFile, int originRank, char destinationFile, int destinationRank) {
        this.piece = piece;
        this.capturedPiece = capturedPiece;
        this.originFile = originFile;
        this.originRank = originRank;
        this.destinationFile = destinationFile;
        this.destinationRank = destinationRank;
        this.valid = false;
    }

    public Piece getPiece() {
        return piece;
    }

    public Piece getCapturedPiece() {
        return capturedPiece;
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

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    public int getTimeUsed() {
        return timeUsed;
    }

}
