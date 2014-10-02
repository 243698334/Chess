package com.kevinychen.chess.main.board;

import com.kevinychen.chess.main.pieces.*;
import com.kevinychen.chess.main.util.Core;
import com.kevinychen.chess.main.util.Move;

import java.util.Iterator;

/**
 * Singleton implementation for the chessboard.
 * The Board class contains a 2D array of Squares.
 */
public class Board {

    public static final int DIMENSION = 8;

    private static Square[][] grid = new Square[DIMENSION][DIMENSION];

    private static Board boardInstance = new Board();

    public static Board getInstance() {
        return boardInstance;
    }

    private Board() {
        initialize();
    }

    public static void initialize() {
        initializeSquares();
        initializePieces();
    }

    public static void initializeCleanBoard() {
        initializeSquares();
    }

    /**
     * Get reference of the Square with given indices of the 2D array.
     *
     * @param x, y indices of the 2D array
     * @return a reference of the Square requested
     */
    public static Square getSquare(int x, int y) {
        return grid[x][y];
    }

    /**
     * Get reference of the Square with given file and rank on the board.
     *
     * @param file, rank
     * @return a reference of the Square requested
     */
    public static Square getSquare(char file, int rank) {
        file = Character.toLowerCase(file);
        if (file < 'a' || file > 'h' || rank < 1 || rank > 8) {
            return null;
        } else {
            return grid[file - 'a'][rank - 1];
        }
    }

    /**
     * Move the piece from the origin to the destination. If a capture is made, collect the reference of the captured piece.
     *
     * @param move a validated move
     */
    public static void executeMove(Move move) {
        Square originSquare = getSquare(move.getOriginFile(), move.getOriginRank());
        Square destinationSquare = getSquare(move.getDestinationFile(), move.getDestinationRank());
        if (destinationSquare.getCurrentPiece() != null) {
            destinationSquare.getCurrentPiece().setCapture(true);
            PieceSet.addCapturedPiece(destinationSquare.getCurrentPiece());
        }
        destinationSquare.setCurrentPiece(originSquare.getCurrentPiece());
        originSquare.setCurrentPiece(null);
    }

    /**
     * Perform an Undo of a certain move
     *
     * @param move a validated move pulled from MoveLogger
     */
    public static void executeUndo(Move move) {
        Square originSquare = getSquare(move.getOriginFile(), move.getOriginRank());
        Square destinationSquare = getSquare(move.getDestinationFile(), move.getDestinationRank());
        originSquare.setCurrentPiece(destinationSquare.getCurrentPiece());
        destinationSquare.setCurrentPiece(null);
        if (move.getCapturedPiece() != null) {
            destinationSquare.setCurrentPiece(move.getCapturedPiece());
        }
    }

    /**
     * Allocate memory for all Squares.
     */
    private static void initializeSquares() {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                grid[i][j] = new Square(i, j);
            }
        }
    }

    /**
     * Putting all pieces to their initial location.
     */
    private static void initializePieces() {
        // pawns
        Iterator<Piece> whitePawnsIterator = PieceSet.getPieces(Piece.Color.WHITE, Piece.Type.PAWN).iterator();
        Iterator<Piece> blackPawnsIterator = PieceSet.getPieces(Piece.Color.BLACK, Piece.Type.PAWN).iterator();
        for (char file = 'a'; file <= 'h'; file++) {
            getSquare(file, 2).setCurrentPiece(whitePawnsIterator.next());
            getSquare(file, 7).setCurrentPiece(blackPawnsIterator.next());
        }

        // rooks
        Iterator<Piece> whiteRooksIterator = PieceSet.getPieces(Piece.Color.WHITE, Piece.Type.ROOK).iterator();
        Iterator<Piece> blackRooksIterator = PieceSet.getPieces(Piece.Color.BLACK, Piece.Type.ROOK).iterator();
        getSquare('a', 1).setCurrentPiece(whiteRooksIterator.next());
        getSquare('h', 1).setCurrentPiece(whiteRooksIterator.next());
        getSquare('a', 8).setCurrentPiece(blackRooksIterator.next());
        getSquare('h', 8).setCurrentPiece(blackRooksIterator.next());

        // knights
        Iterator<Piece> whiteKnightsIterator = PieceSet.getPieces(Piece.Color.WHITE, Piece.Type.KNIGHT).iterator();
        Iterator<Piece> blackKnightsIterator = PieceSet.getPieces(Piece.Color.BLACK, Piece.Type.KNIGHT).iterator();
        getSquare('b', 1).setCurrentPiece(whiteKnightsIterator.next());
        getSquare('g', 1).setCurrentPiece(whiteKnightsIterator.next());
        getSquare('b', 8).setCurrentPiece(blackKnightsIterator.next());
        getSquare('g', 8).setCurrentPiece(blackKnightsIterator.next());

        // bishops
        Iterator<Piece> whiteBishopsIterator = PieceSet.getPieces(Piece.Color.WHITE, Piece.Type.BISHOP).iterator();
        Iterator<Piece> blackBishopsIterator = PieceSet.getPieces(Piece.Color.BLACK, Piece.Type.BISHOP).iterator();
        getSquare('c', 1).setCurrentPiece(whiteBishopsIterator.next());
        getSquare('f', 1).setCurrentPiece(whiteBishopsIterator.next());
        getSquare('c', 8).setCurrentPiece(blackBishopsIterator.next());
        getSquare('f', 8).setCurrentPiece(blackBishopsIterator.next());

        // queens
        Iterator<Piece> whiteQueenIterator = PieceSet.getPieces(Piece.Color.WHITE, Piece.Type.QUEEN).iterator();
        Iterator<Piece> blackQueenIterator = PieceSet.getPieces(Piece.Color.BLACK, Piece.Type.QUEEN).iterator();
        getSquare('d', 1).setCurrentPiece(whiteQueenIterator.next());
        getSquare('d', 8).setCurrentPiece(blackQueenIterator.next());

        // kings
        Iterator<Piece> whiteKingIterator = PieceSet.getPieces(Piece.Color.WHITE, Piece.Type.KING).iterator();
        Iterator<Piece> blackKingIterator = PieceSet.getPieces(Piece.Color.BLACK, Piece.Type.KING).iterator();
        getSquare('e', 1).setCurrentPiece(whiteKingIterator.next());
        getSquare('e', 8).setCurrentPiece(blackKingIterator.next());

        if (Core.getPreferences().isUsingCustomPieces()) {
            // cannons
            Iterator<Piece> whiteCannonIterator = PieceSet.getPieces(Piece.Color.WHITE, Piece.Type.CANNON).iterator();
            Iterator<Piece> blackCannonIterator = PieceSet.getPieces(Piece.Color.BLACK, Piece.Type.CANNON).iterator();
            getSquare('a', 1).setCurrentPiece(whiteCannonIterator.next());
            getSquare('h', 8).setCurrentPiece(blackCannonIterator.next());

            // shields
            Iterator<Piece> whiteShieldIterator = PieceSet.getPieces(Piece.Color.WHITE, Piece.Type.SHIELD).iterator();
            Iterator<Piece> blackShieldIterator = PieceSet.getPieces(Piece.Color.BLACK, Piece.Type.SHIELD).iterator();
            getSquare('f', 1).setCurrentPiece(whiteShieldIterator.next());
            getSquare('c', 8).setCurrentPiece(blackShieldIterator.next());
        }
    }
}
