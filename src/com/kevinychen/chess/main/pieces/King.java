package com.kevinychen.chess.main.pieces;

import com.kevinychen.chess.main.util.Move;

public class King extends Piece {

    public King(Color color) {
        super(color);
        this.type = Type.KING;
    }

    @Override
    public boolean validateMove(Move move) {
        // executeMove or capture
        if ((move.getCapturedPiece() == null)
                || (move.getCapturedPiece() != null
                && !move.getPiece().getColor().equals(move.getCapturedPiece().getColor())
                && !move.getCapturedPiece().getType().equals(Piece.Type.SHIELD))) {
            // one square executeMove
            if (Math.abs(move.getDestinationFile() - move.getOriginFile()) <= 1
                    && Math.abs(move.getDestinationRank() - move.getOriginRank()) <= 1) {
                return true;
            }
        }

        // all other cases
        return false;
    }

}
