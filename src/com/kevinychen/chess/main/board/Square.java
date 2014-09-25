package com.kevinychen.chess.main.board;

import com.kevinychen.chess.main.pieces.Piece;

/**
 * Created by KevinC on 9/10/14.
 */
public class Square {

    private int xCoordinate;
    private int yCoordinate;

    private char file;
    private int rank;

    private Piece currentPiece;

    public Square(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.currentPiece = null;
    }

    public Square(int xCoordinate, int yCoordinate, Piece initialPiece) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.currentPiece = initialPiece;
    }

    public Square(char file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public int getX() {
        return this.xCoordinate;
    }

    public int getY() {
        return this.yCoordinate;
    }

    public void setCurrentPiece(Piece piece) {
        this.currentPiece = piece;
    }

    public Piece getCurrentPiece() {
        return this.currentPiece;
    }

}
