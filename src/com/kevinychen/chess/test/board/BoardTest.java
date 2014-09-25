package com.kevinychen.chess.test.board;

import com.kevinychen.chess.main.board.Board;
import com.kevinychen.chess.main.pieces.Piece;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void testBoardInitialize() {
        Board.initialize();

        // empty squares
        for (char file = 'a'; file <= 'h'; file++) {
            for (int rank = 3; rank <= 6; rank++) {
                assertNull(Board.getSquare(file, rank).getCurrentPiece());
            }
        }

        // pawns
        for (char file = 'a'; file <= 'h'; file++) {
            assertEquals(Piece.Type.PAWN, Board.getSquare(file, 2).getCurrentPiece().getType());
            assertEquals(Piece.Color.WHITE, Board.getSquare(file, 2).getCurrentPiece().getColor());
            assertEquals(Piece.Type.PAWN, Board.getSquare(file, 7).getCurrentPiece().getType());
            assertEquals(Piece.Color.BLACK, Board.getSquare(file, 7).getCurrentPiece().getColor());
        }

        // rooks
        assertEquals(Piece.Type.ROOK, Board.getSquare('a', 1).getCurrentPiece().getType());
        assertEquals(Piece.Color.WHITE, Board.getSquare('a', 1).getCurrentPiece().getColor());
        assertEquals(Piece.Type.ROOK, Board.getSquare('h', 1).getCurrentPiece().getType());
        assertEquals(Piece.Color.WHITE, Board.getSquare('h', 1).getCurrentPiece().getColor());
        assertEquals(Piece.Type.ROOK, Board.getSquare('a', 8).getCurrentPiece().getType());
        assertEquals(Piece.Color.BLACK, Board.getSquare('a', 8).getCurrentPiece().getColor());
        assertEquals(Piece.Type.ROOK, Board.getSquare('h', 8).getCurrentPiece().getType());
        assertEquals(Piece.Color.BLACK, Board.getSquare('h', 8).getCurrentPiece().getColor());

        // knights
        assertEquals(Piece.Type.KNIGHT, Board.getSquare('b', 1).getCurrentPiece().getType());
        assertEquals(Piece.Color.WHITE, Board.getSquare('b', 1).getCurrentPiece().getColor());
        assertEquals(Piece.Type.KNIGHT, Board.getSquare('g', 1).getCurrentPiece().getType());
        assertEquals(Piece.Color.WHITE, Board.getSquare('h', 1).getCurrentPiece().getColor());
        assertEquals(Piece.Type.KNIGHT, Board.getSquare('b', 8).getCurrentPiece().getType());
        assertEquals(Piece.Color.BLACK, Board.getSquare('g', 8).getCurrentPiece().getColor());
        assertEquals(Piece.Type.KNIGHT, Board.getSquare('b', 8).getCurrentPiece().getType());
        assertEquals(Piece.Color.BLACK, Board.getSquare('g', 8).getCurrentPiece().getColor());

        // bishops
        assertEquals(Piece.Type.BISHOP, Board.getSquare('c', 1).getCurrentPiece().getType());
        assertEquals(Piece.Color.WHITE, Board.getSquare('c', 1).getCurrentPiece().getColor());
        assertEquals(Piece.Type.BISHOP, Board.getSquare('f', 1).getCurrentPiece().getType());
        assertEquals(Piece.Color.WHITE, Board.getSquare('f', 1).getCurrentPiece().getColor());
        assertEquals(Piece.Type.BISHOP, Board.getSquare('c', 8).getCurrentPiece().getType());
        assertEquals(Piece.Color.BLACK, Board.getSquare('c', 8).getCurrentPiece().getColor());
        assertEquals(Piece.Type.BISHOP, Board.getSquare('f', 8).getCurrentPiece().getType());
        assertEquals(Piece.Color.BLACK, Board.getSquare('f', 8).getCurrentPiece().getColor());

        // kings
        assertEquals(Piece.Type.KING, Board.getSquare('d', 1).getCurrentPiece().getType());
        assertEquals(Piece.Color.WHITE, Board.getSquare('d', 1).getCurrentPiece().getColor());
        assertEquals(Piece.Type.KING, Board.getSquare('d', 8).getCurrentPiece().getType());
        assertEquals(Piece.Color.BLACK, Board.getSquare('d', 8).getCurrentPiece().getColor());

        // queens
        assertEquals(Piece.Type.QUEEN, Board.getSquare('e', 1).getCurrentPiece().getType());
        assertEquals(Piece.Color.WHITE, Board.getSquare('e', 1).getCurrentPiece().getColor());
        assertEquals(Piece.Type.QUEEN, Board.getSquare('e', 8).getCurrentPiece().getType());
        assertEquals(Piece.Color.BLACK, Board.getSquare('e', 8).getCurrentPiece().getColor());
    }

    @Test
    public void testMove() {

    }
}
