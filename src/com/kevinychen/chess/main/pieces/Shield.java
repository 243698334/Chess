package com.kevinychen.chess.main.pieces;

import com.kevinychen.chess.main.util.Move;

public class Shield extends Piece {
    public Shield(Color color) {
        super(color);
        this.type = Type.SHIELD;
    }

    @Override
    public boolean validateMove(Move move) {
        // executeMove only
        if (move.getCapturedPiece() == null) {
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
