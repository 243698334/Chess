package com.kevinychen.chess.main.pieces;

import com.kevinychen.chess.main.board.Board;
import com.kevinychen.chess.main.util.Move;

public class Cannon extends Piece {
    public Cannon(Color color) {
        super(color);
        this.type = Type.CANNON;
    }

    @Override
    public boolean validateMove(Move move) {
        int fileDirection = Integer.signum(move.getDestinationFile() - move.getOriginFile());
        int rankDirection = Integer.signum(move.getDestinationRank() - move.getOriginRank());

        // move
        if (move.getCapturedPiece() == null) {
            // along file
            if (move.getDestinationFile() == move.getOriginFile()
                    && move.getDestinationRank() != move.getOriginRank()) {
                for (int rank = move.getOriginRank() + rankDirection; rank != move.getDestinationRank(); rank += rankDirection) {
                    if (Board.getSquare(move.getOriginFile(), rank).getCurrentPiece() != null) {
                        return false;
                    }
                }
            }
            // along rank
            if (move.getDestinationFile() != move.getOriginFile()
                    && move.getDestinationRank() == move.getOriginRank()) {
                for (char file = (char) (move.getOriginFile() + fileDirection); file != move.getDestinationFile(); file += fileDirection) {
                    if (Board.getSquare(file, move.getOriginRank()).getCurrentPiece() != null) {
                        return false;
                    }
                }
            }
        }

        // capture
        if (move.getCapturedPiece() != null && !move.getCapturedPiece().getType().equals(Piece.Type.SHIELD)) {
            int hurdleCount = 0;
            // along file
            if (move.getDestinationFile() - move.getOriginFile() == 0
                    && move.getDestinationRank() - move.getOriginRank() != 0) {
                for (int rank = move.getOriginRank() + rankDirection; rank != move.getDestinationRank(); rank += rankDirection) {
                    if (Board.getSquare(move.getOriginFile(), rank).getCurrentPiece() != null) {
                        hurdleCount++;
                    }
                }
            }

            // along rank
            if (move.getDestinationFile() - move.getOriginFile() != 0
                    && move.getDestinationRank() - move.getOriginRank() == 0) {
                for (char file = (char) (move.getOriginFile() + fileDirection); file != move.getDestinationFile(); file += fileDirection) {
                    if (Board.getSquare(file, move.getOriginRank()).getCurrentPiece() != null) {
                        hurdleCount++;
                    }
                }
            }
            if (hurdleCount == 1) {
                return Board.getSquare(move.getDestinationFile(), move.getDestinationRank()).getCurrentPiece() != null;
            }
        }

        // all other cases
        return false;
    }
}
