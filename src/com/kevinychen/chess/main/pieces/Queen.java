package com.kevinychen.chess.main.pieces;

import com.kevinychen.chess.main.util.Move;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
        this.type = Type.QUEEN;
    }

    @Override
    public boolean validateMove(Move move) {
        // executeMove or capture
        if ((move.getCapturedPiece() == null)
                || (move.getCapturedPiece() != null
                && !move.getPiece().getColor().equals(move.getCapturedPiece().getColor())
                && !move.getCapturedPiece().getType().equals(Piece.Type.SHIELD))) {
            // along file
            if (move.getDestinationFile() == move.getOriginFile()
                    && move.getDestinationRank() != move.getOriginRank()) {
                return true;
            }
            // along rank
            if (move.getDestinationFile() != move.getOriginFile()
                    && move.getDestinationRank() == move.getOriginRank()) {
                return true;
            }
            // diagonal
            if (Math.abs(move.getDestinationFile() - move.getOriginFile())
                    == Math.abs(move.getDestinationRank() - move.getOriginRank())) {
                return true;
            }
        }

        // all other cases
        return false;
    }

}
