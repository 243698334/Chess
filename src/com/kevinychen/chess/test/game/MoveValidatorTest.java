package com.kevinychen.chess.test.game;

import com.kevinychen.chess.main.board.Board;
import com.kevinychen.chess.main.util.Move;
import com.kevinychen.chess.main.util.MoveValidator;
import com.kevinychen.chess.main.pieces.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoveValidatorTest {

    @Test
    public void testPawnMove() {
        // initial executeMove
        Board.initialize();
        assertTrue(MoveValidator.validateMove(new Move('d', 2, 'd', 3), true));
        assertTrue(MoveValidator.validateMove(new Move('e', 7, 'e', 5), true));
        assertFalse(MoveValidator.validateMove(new Move('c', 2, 'c', 5), true));

        // capture
        Board.initializeCleanBoard();
        Board.getSquare('d', 4).setCurrentPiece(new Pawn(Piece.Color.WHITE));
        Board.getSquare('e', 5).setCurrentPiece(new Pawn(Piece.Color.BLACK));
        assertTrue(MoveValidator.validateMove(new Move('d', 4, 'e', 5), true));
        assertTrue(MoveValidator.validateMove(new Move('e', 5, 'd', 4), true));
        Board.getSquare('c', 5).setCurrentPiece(new Pawn(Piece.Color.WHITE));
        Board.getSquare('f', 4).setCurrentPiece(new Pawn(Piece.Color.BLACK));
        assertFalse(MoveValidator.validateMove(new Move('d', 4, 'c', 5), true));
        assertFalse(MoveValidator.validateMove(new Move('e', 5, 'f', 4), true));
        Board.getSquare('c', 5).setCurrentPiece(null);
        Board.getSquare('f', 4).setCurrentPiece(null);
        assertFalse(MoveValidator.validateMove(new Move('d', 4, 'c', 5), true));
        assertFalse(MoveValidator.validateMove(new Move('e', 5, 'f', 4), true));
        assertFalse(MoveValidator.validateMove(new Move('d', 4, 'c', 3), true));
        assertFalse(MoveValidator.validateMove(new Move('e', 5, 'f', 6), true));

        // forward
        Board.initializeCleanBoard();
        Board.getSquare('d', 3).setCurrentPiece(new Pawn(Piece.Color.WHITE));
        Board.getSquare('e', 6).setCurrentPiece(new Pawn(Piece.Color.BLACK));
        assertTrue(MoveValidator.validateMove(new Move('d', 3, 'd', 4), true));
        assertFalse(MoveValidator.validateMove(new Move('d', 3, 'd', 5), true));
        assertFalse(MoveValidator.validateMove(new Move('d', 3, 'd', 2), true));
        assertFalse(MoveValidator.validateMove(new Move('d', 3, 'e', 3), true));

        // overlap or collision
        Board.initializeCleanBoard();
        Board.getSquare('d', 3).setCurrentPiece(new Pawn(Piece.Color.WHITE));
        assertTrue(MoveValidator.validateMove(new Move('d', 3, 'd', 4), true));
        Board.getSquare('d', 4).setCurrentPiece(new Rook(Piece.Color.WHITE));
        assertFalse(MoveValidator.validateMove(new Move('d', 3, 'd', 4), true));
        Board.getSquare('e', 7).setCurrentPiece(new Pawn(Piece.Color.BLACK));
        assertTrue(MoveValidator.validateMove(new Move('e', 7, 'e', 5), true));
        Board.getSquare('e', 6).setCurrentPiece(new Rook(Piece.Color.BLACK));
        assertFalse(MoveValidator.validateMove(new Move('e', 7, 'e', 5), true));
    }

    @Test
    public void testRookMove() {
        Board.initializeCleanBoard();

        // executeMove
        Board.getSquare('d', 4).setCurrentPiece(new Rook(Piece.Color.WHITE));
        assertTrue(MoveValidator.validateMove(new Move('d', 4, 'd', 8), true));
        assertTrue(MoveValidator.validateMove(new Move('d', 4, 'd', 1), true));
        assertTrue(MoveValidator.validateMove(new Move('d', 4, 'a', 4), true));
        assertTrue(MoveValidator.validateMove(new Move('d', 4, 'g', 4), true));
        assertFalse(MoveValidator.validateMove(new Move('d', 4, 'f', 6), true));

        // capture
        Board.getSquare('d', 7).setCurrentPiece(new Rook(Piece.Color.BLACK));
        assertTrue(MoveValidator.validateMove(new Move('d', 4, 'd', 7), true));
        Board.getSquare('a', 4).setCurrentPiece(new Rook(Piece.Color.WHITE));
        assertFalse(MoveValidator.validateMove(new Move('d', 4, 'a', 4), true));
        Board.getSquare('c', 5).setCurrentPiece(new Rook(Piece.Color.BLACK));
        assertFalse(MoveValidator.validateMove(new Move('d', 4, 'c', 5), true));

        // overlap or collision
        Board.getSquare('d', 6).setCurrentPiece(new Pawn(Piece.Color.BLACK));
        assertFalse(MoveValidator.validateMove(new Move('d', 4, 'd', 7), true));
    }

    @Test
    public void testKnightMove() {
        Board.initializeCleanBoard();

        // executeMove
        Board.getSquare('b', 1).setCurrentPiece(new Knight(Piece.Color.WHITE));
        assertTrue(MoveValidator.validateMove(new Move('b', 1, 'c', 3), true));
        assertFalse(MoveValidator.validateMove(new Move('b', 1, 'c', 2), true));

        // capture
        Board.getSquare('a', 3).setCurrentPiece(new Rook(Piece.Color.BLACK));
        assertTrue(MoveValidator.validateMove(new Move('b', 1, 'a', 3), true));
        Board.getSquare('d', 2).setCurrentPiece(new Rook(Piece.Color.WHITE));
        assertFalse(MoveValidator.validateMove(new Move('b', 1, 'd', 2), true));
        assertFalse(MoveValidator.validateMove(new Move('b', 1, 'd', 3), true));
    }

    @Test
    public void testBishopMove() {
        Board.initializeCleanBoard();

        // executeMove
        Board.getSquare('c', 1).setCurrentPiece(new Bishop(Piece.Color.WHITE));
        assertTrue(MoveValidator.validateMove(new Move('c', 1, 'f', 4), true));
        assertFalse(MoveValidator.validateMove(new Move('c', 1, 'd', 3), true));

        // capture
        Board.getSquare('g', 5).setCurrentPiece(new Rook(Piece.Color.BLACK));
        assertTrue(MoveValidator.validateMove(new Move('c', 1, 'g', 5), true));
        Board.getSquare('a', 3).setCurrentPiece(new Rook(Piece.Color.WHITE));
        assertFalse(MoveValidator.validateMove(new Move('c', 1, 'a', 3), true));

        // overlap or collision
        Board.getSquare('f', 4).setCurrentPiece(new Bishop(Piece.Color.WHITE));
        assertFalse(MoveValidator.validateMove(new Move('c', 1, 'g', 5), true));
    }

    @Test
    public void testKingMove() {
        Board.initializeCleanBoard();

        // executeMove
        Board.getSquare('d', 2).setCurrentPiece(new King(Piece.Color.WHITE));
        assertTrue(MoveValidator.validateMove(new Move('d', 2, 'c', 3), true));
        assertTrue(MoveValidator.validateMove(new Move('d', 2, 'e', 2), true));
        assertTrue(MoveValidator.validateMove(new Move('d', 2, 'd', 1), true));
        assertFalse(MoveValidator.validateMove(new Move('d', 2, 'f', 2), true));

        // capture
        Board.getSquare('c', 1).setCurrentPiece(new Rook(Piece.Color.BLACK));
        assertTrue(MoveValidator.validateMove(new Move('d', 2, 'c', 1), true));
        Board.getSquare('c', 2).setCurrentPiece(new Rook(Piece.Color.WHITE));
        assertFalse(MoveValidator.validateMove(new Move('d', 2, 'c', 2), true));
    }

    @Test
    public void testQueenMove() {
        Board.initializeCleanBoard();

        // executeMove
        Board.getSquare('e', 5).setCurrentPiece(new Queen(Piece.Color.WHITE));
        assertTrue(MoveValidator.validateMove(new Move('e', 5, 'c', 3), true));
        assertTrue(MoveValidator.validateMove(new Move('e', 5, 'e', 3), true));
        assertTrue(MoveValidator.validateMove(new Move('e', 5, 'h', 2), true));
        assertTrue(MoveValidator.validateMove(new Move('e', 5, 'g', 5), true));
        assertFalse(MoveValidator.validateMove(new Move('e', 5, 'd', 3), true));

        // capture
        Board.getSquare('h', 8).setCurrentPiece(new Queen(Piece.Color.BLACK));
        assertTrue(MoveValidator.validateMove(new Move('e', 5, 'h', 8), true));
        Board.getSquare('e', 8).setCurrentPiece(new Knight(Piece.Color.WHITE));
        assertFalse(MoveValidator.validateMove(new Move('e', 5, 'e', 8), true));

        // collision
        Board.getSquare('g', 7).setCurrentPiece(new Pawn(Piece.Color.BLACK));
        assertFalse(MoveValidator.validateMove(new Move('e', 5, 'h', 8), true));
        Board.getSquare('e', 7).setCurrentPiece(new Pawn(Piece.Color.BLACK));
        assertFalse(MoveValidator.validateMove(new Move('e', 5, 'e', 8), true));
    }

    @Test
    public void testCannonMove() {
        Board.initializeCleanBoard();

        // executeMove
        Board.getSquare('d', 4).setCurrentPiece(new Cannon(Piece.Color.WHITE));
        assertTrue(MoveValidator.validateMove(new Move('d', 4, 'd', 8), true));
        assertTrue(MoveValidator.validateMove(new Move('d', 4, 'd', 1), true));
        assertTrue(MoveValidator.validateMove(new Move('d', 4, 'a', 4), true));
        assertTrue(MoveValidator.validateMove(new Move('d', 4, 'g', 4), true));
        assertFalse(MoveValidator.validateMove(new Move('d', 4, 'f', 6), true));

        // capture
        Board.getSquare('d', 8).setCurrentPiece(new Shield(Piece.Color.BLACK));
        assertFalse(MoveValidator.validateMove(new Move('d', 4, 'd', 8), true));
        Board.getSquare('d', 5).setCurrentPiece(new Pawn(Piece.Color.WHITE));
        assertTrue(MoveValidator.validateMove(new Move('d', 4, 'd', 8), true));
        Board.getSquare('d', 7).setCurrentPiece(new Rook(Piece.Color.BLACK));
        assertFalse(MoveValidator.validateMove(new Move('d', 4, 'd', 8), true));
        assertFalse(MoveValidator.validateMove(new Move('d', 4, 'd', 7), true));
    }

    @Test
    public void testShieldMove() {
        Board.initializeCleanBoard();

        // executeMove
        Board.getSquare('d', 2).setCurrentPiece(new Shield(Piece.Color.WHITE));
        assertTrue(MoveValidator.validateMove(new Move('d', 2, 'c', 3), true));
        assertTrue(MoveValidator.validateMove(new Move('d', 2, 'e', 2), true));
        assertTrue(MoveValidator.validateMove(new Move('d', 2, 'd', 1), true));
        assertFalse(MoveValidator.validateMove(new Move('d', 2, 'f', 2), true));

        // collision
        Board.getSquare('c', 1).setCurrentPiece(new Rook(Piece.Color.BLACK));
        assertFalse(MoveValidator.validateMove(new Move('d', 2, 'c', 1), true));
        Board.getSquare('c', 2).setCurrentPiece(new Rook(Piece.Color.WHITE));
        assertFalse(MoveValidator.validateMove(new Move('d', 2, 'c', 2), true));
    }

    @Test
    public void testCheckMove() {
        Board.initializeCleanBoard();
        Board.getSquare('d', 8).setCurrentPiece(new King(Piece.Color.BLACK));
        Board.getSquare('e', 6).setCurrentPiece(new Queen(Piece.Color.WHITE));
        assertTrue(MoveValidator.isCheckMove(new Move('e', 6, 'f', 6)));
        assertTrue(MoveValidator.isCheckMove(new Move('e', 6, 'd', 6)));
        assertFalse(MoveValidator.isCheckMove(new Move('e', 6, 'c', 6)));
        Board.getSquare('e', 7).setCurrentPiece(new Pawn(Piece.Color.BLACK));
        assertFalse(MoveValidator.isCheckMove(new Move('e', 6, 'f', 6)));
    }

}
