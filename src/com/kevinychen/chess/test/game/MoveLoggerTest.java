package com.kevinychen.chess.test.game;

import com.kevinychen.chess.main.board.Board;
import com.kevinychen.chess.main.util.Move;
import com.kevinychen.chess.main.util.MoveLogger;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoveLoggerTest {

    @Test
    public void testAddMove() {
        Board.initialize();
        Move pawnC2C4 = new Move('c', 2, 'c', 4);
        MoveLogger.addMove(pawnC2C4);
        Move pawnC7C5 = new Move('c', 7, 'c', 5);
        MoveLogger.addMove(pawnC7C5);
        Move knightG1F3 = new Move('g', 1, 'f', 3);
        MoveLogger.addMove(knightG1F3);
        Move knightG8H6 = new Move('g', 8, 'h', 6);
        MoveLogger.addMove(knightG8H6);
        Move pawnH2H3 = new Move('h', 2, 'h', 3);
        MoveLogger.addMove(pawnH2H3);
        assertEquals(2, MoveLogger.getCount());
        assertFalse(MoveLogger.isFullRound());
    }

    @Test
    public void testLastMove() {
        Board.initialize();
        Move pawnC2C4 = new Move('c', 2, 'c', 4);
        MoveLogger.addMove(pawnC2C4);
        Move pawnC7C5 = new Move('c', 7, 'c', 5);
        MoveLogger.addMove(pawnC7C5);
        Move knightG1F3 = new Move('g', 1, 'f', 3);
        MoveLogger.addMove(knightG1F3);
        Move knightG8H6 = new Move('g', 8, 'h', 6);
        MoveLogger.addMove(knightG8H6);
        Move pawnH2H3 = new Move('h', 2, 'h', 3);
        MoveLogger.addMove(pawnH2H3);
        assertEquals(pawnH2H3, MoveLogger.getLastMove());

        Move knightH6F5 = new Move('h', 6, 'f', 5);
        MoveLogger.addMove(knightH6F5);
        assertEquals(knightH6F5, MoveLogger.getLastMove());
    }

    @Test
    public void testUndoMove() {
        Board.initialize();
        Move pawnC2C4 = new Move('c', 2, 'c', 4);
        MoveLogger.addMove(pawnC2C4);
        Move pawnC7C5 = new Move('c', 7, 'c', 5);
        MoveLogger.addMove(pawnC7C5);
        Move knightG1F3 = new Move('g', 1, 'f', 3);
        MoveLogger.addMove(knightG1F3);
        Move knightG8H6 = new Move('g', 8, 'h', 6);
        MoveLogger.addMove(knightG8H6);
        Move pawnH2H3 = new Move('h', 2, 'h', 3);
        MoveLogger.addMove(pawnH2H3);
        assertEquals(pawnH2H3, MoveLogger.undoLastMove());
        assertEquals(knightG8H6, MoveLogger.getLastMove());
    }
}
