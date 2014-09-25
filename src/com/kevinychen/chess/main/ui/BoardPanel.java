package com.kevinychen.chess.main.ui;

import com.kevinychen.chess.main.pieces.Piece;
import com.kevinychen.chess.main.pieces.PieceSet;
import com.kevinychen.chess.main.util.GameModel;
import com.kevinychen.chess.main.util.Move;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

public class BoardPanel extends JPanel implements Observer {

    public static final int SQUARE_DIMENSION = 100;

    private GameModel gameModel;
    private boolean reverse;
    private JPanel[][] squarePanels;
    private Image draggedPieceImage;

    /**
     * Constructor for the BoardPanel.
     *
     * @param gameModel Reference to the GameModel instance
     */
    public BoardPanel(GameModel gameModel) {
        super(new GridLayout(8, 8));
        this.gameModel = gameModel;
        this.reverse = gameModel.isReverseBoard();
        initializeSquares();
        initializePieces();
        initializeDragAndDropListener();
        gameModel.addObserver(this);
    }

    /**
     * Submit a move request to the GameModel, called by the PieceDragAndDropListener
     *
     * @param originFile
     * @param originRank
     * @param destinationFile
     * @param destinationRank
     */
    public void submitMoveRequest(char originFile, int originRank, char destinationFile, int destinationRank) {
        draggedPieceImage = null;
        if (getSquarePanel(originFile, originRank).getComponent(0) != null ) {
            getSquarePanel(originFile, originRank).getComponent(0).setVisible(true);
            gameModel.onMoveRequest(originFile, originRank, destinationFile, destinationRank);
        }
    }

    /**
     * Execute the move on the board, called by the GameModel
     *
     * @param move A validated Move object
     */
    public void executeMove(Move move) {
        JPanel originSquarePanel = getSquarePanel(move.getOriginFile(), move.getOriginRank());
        JPanel destinationSquarePanel = getSquarePanel(move.getDestinationFile(), move.getDestinationRank());
        destinationSquarePanel.removeAll();
        destinationSquarePanel.add(originSquarePanel.getComponent(0));
        destinationSquarePanel.repaint();
        originSquarePanel.removeAll();
        originSquarePanel.repaint();
    }

    /**
     * Execute an undo of the given move, called by the GameModel
     *
     * @param move A validated Move object of the last move just made
     */
    public void executeUndo(Move move) {
        JPanel originSquarePanel = getSquarePanel(move.getOriginFile(), move.getOriginRank());
        JPanel destinationSquarePanel = getSquarePanel(move.getDestinationFile(), move.getDestinationRank());
        originSquarePanel.add(destinationSquarePanel.getComponent(0));
        destinationSquarePanel.removeAll();
        if (move.getCapturedPiece() != null) {
            destinationSquarePanel.add(getPieceImageLabel(move.getCapturedPiece()));
        }
        originSquarePanel.repaint();
        destinationSquarePanel.repaint();
    }

    /**
     * Execute a drag effect, called by the PieceDragAndDropListener
     *
     * @param originFile File value of the origin square
     * @param originRank Rank value of the origin square
     * @param x X coordinate on the BoardPanel where the image of the piece is shown
     * @param y Y coordinate on the BoardPanel where the image of the piece is shown
     */
    public void executeDrag(char originFile, int originRank, int x, int y) {
        Piece originPiece = gameModel.onQueryRequest(originFile, originRank);
        if (originPiece != null) {
            getSquarePanel(originFile, originRank).getComponent(0).setVisible(false);
            draggedPieceImage = getPieceImage(originPiece);
            if (draggedPieceImage == null || draggedPieceImage.getHeight(null) == 0) {
                System.out.println("NULL!");
            }
            JLabel draggedPieceImageLabel = getPieceImageLabel(originPiece);
            draggedPieceImageLabel.setBounds(x, y, SQUARE_DIMENSION, SQUARE_DIMENSION);
            draggedPieceImageLabel.setVisible(true);
            this.getParent().getGraphics().drawImage(draggedPieceImage, x, y, null);
            this.validate();
            this.repaint();
            SwingUtilities.getWindowAncestor(this).repaint();
        }
    }

    /**
     * Get the JPanel of the requested Square by file and rank
     *
     * @param file
     * @param rank
     * @return
     */
    public JPanel getSquarePanel(char file, int rank) {
        file = Character.toLowerCase(file);
        if (file < 'a' || file > 'h' || rank < 1 || rank > 8) {
            return null;
        } else {
            return squarePanels[file - 'a'][rank - 1];
        }
    }

    /**
     * Initialize the JPanels for Squares based on if the board is reversed.
     */
    private void initializeSquares() {
        squarePanels = new JPanel[8][8];
        if (reverse) {
            for (int r = 0; r < 8; r ++) {
                for (int f = 7; f >= 0; f--) {
                    initializeSingleSquarePanel(f, r);
                }
            }
        } else {
            for (int r = 7; r >= 0; r --) {
                for (int f = 0; f < 8; f++) {
                    initializeSingleSquarePanel(f, r);
                }
            }
        }
    }

    private void initializeSingleSquarePanel(int f, int r) {
        squarePanels[f][r] = new JPanel(new GridLayout(1, 1));
        squarePanels[f][r].setPreferredSize(new Dimension(SQUARE_DIMENSION, SQUARE_DIMENSION));
        squarePanels[f][r].setSize(new Dimension(SQUARE_DIMENSION, SQUARE_DIMENSION));
        squarePanels[f][r].setBackground(f % 2 == r % 2 ? Color.GRAY : Color.WHITE);
        this.add(squarePanels[f][r]);
        // test
        //JLabel coordinates = new JLabel((char)('a'+f) + ", " + r);
        //squarePanels[f][r].add(coordinates);
    }

    /**
     * Initialize the pieces to default location with no customized pieces.
     */
    private void initializePieces() {
        // pawns
        Iterator<Piece> whitePawnsIterator = PieceSet.getPieces(Piece.Color.WHITE, Piece.Type.PAWN).iterator();
        Iterator<Piece> blackPawnsIterator = PieceSet.getPieces(Piece.Color.BLACK, Piece.Type.PAWN).iterator();
        for (char file = 'a'; file <= 'h'; file++) {
            getSquarePanel(file, 2).add(getPieceImageLabel(whitePawnsIterator.next()));
            getSquarePanel(file, 7).add(getPieceImageLabel(blackPawnsIterator.next()));
        }

        // rooks
        Iterator<Piece> whiteRooksIterator = PieceSet.getPieces(Piece.Color.WHITE, Piece.Type.ROOK).iterator();
        Iterator<Piece> blackRooksIterator = PieceSet.getPieces(Piece.Color.BLACK, Piece.Type.ROOK).iterator();
        getSquarePanel('a', 1).add(getPieceImageLabel(whiteRooksIterator.next()));
        getSquarePanel('h', 1).add(getPieceImageLabel(whiteRooksIterator.next()));
        getSquarePanel('a', 8).add(getPieceImageLabel(blackRooksIterator.next()));
        getSquarePanel('h', 8).add(getPieceImageLabel(blackRooksIterator.next()));

        // knights
        Iterator<Piece> whiteKnightsIterator = PieceSet.getPieces(Piece.Color.WHITE, Piece.Type.KNIGHT).iterator();
        Iterator<Piece> blackKnightsIterator = PieceSet.getPieces(Piece.Color.BLACK, Piece.Type.KNIGHT).iterator();
        getSquarePanel('b', 1).add(getPieceImageLabel(whiteKnightsIterator.next()));
        getSquarePanel('g', 1).add(getPieceImageLabel(whiteKnightsIterator.next()));
        getSquarePanel('b', 8).add(getPieceImageLabel(blackKnightsIterator.next()));
        getSquarePanel('g', 8).add(getPieceImageLabel(blackKnightsIterator.next()));

        // bishops
        Iterator<Piece> whiteBishopsIterator = PieceSet.getPieces(Piece.Color.WHITE, Piece.Type.BISHOP).iterator();
        Iterator<Piece> blackBishopsIterator = PieceSet.getPieces(Piece.Color.BLACK, Piece.Type.BISHOP).iterator();
        getSquarePanel('c', 1).add(getPieceImageLabel(whiteBishopsIterator.next()));
        getSquarePanel('f', 1).add(getPieceImageLabel(whiteBishopsIterator.next()));
        getSquarePanel('c', 8).add(getPieceImageLabel(blackBishopsIterator.next()));
        getSquarePanel('f', 8).add(getPieceImageLabel(blackBishopsIterator.next()));

        // queens
        Iterator<Piece> whiteQueenIterator = PieceSet.getPieces(Piece.Color.WHITE, Piece.Type.QUEEN).iterator();
        Iterator<Piece> blackQueenIterator = PieceSet.getPieces(Piece.Color.BLACK, Piece.Type.QUEEN).iterator();
        getSquarePanel('d', 1).add(getPieceImageLabel(whiteQueenIterator.next()));
        getSquarePanel('d', 8).add(getPieceImageLabel(blackQueenIterator.next()));

        // kings
        Iterator<Piece> whiteKingIterator = PieceSet.getPieces(Piece.Color.WHITE, Piece.Type.KING).iterator();
        Iterator<Piece> blackKingIterator = PieceSet.getPieces(Piece.Color.BLACK, Piece.Type.KING).iterator();
        getSquarePanel('e', 1).add(getPieceImageLabel(whiteKingIterator.next()));
        getSquarePanel('e', 8).add(getPieceImageLabel(blackKingIterator.next()));
    }

    private void initializeDragAndDropListener() {
        PieceDragAndDropListener pieceDragAndDropListener = new PieceDragAndDropListener();
        this.addMouseListener(pieceDragAndDropListener);
        this.addMouseMotionListener(pieceDragAndDropListener);
    }

    private JLabel getPieceImageLabel(Piece piece) {
        Image pieceImage = new ImageIcon(getClass().getResource(piece.getImageFileName())).getImage();
        pieceImage = pieceImage.getScaledInstance(SQUARE_DIMENSION, SQUARE_DIMENSION, Image.SCALE_SMOOTH);
        JLabel pieceImageLabel = new JLabel(new ImageIcon(pieceImage));
        return pieceImageLabel;
    }

    private Image getPieceImage(Piece piece) {
        Image pieceImage = new ImageIcon(getClass().getResource(piece.getImageFileName())).getImage();
        return pieceImage.getScaledInstance(SQUARE_DIMENSION, SQUARE_DIMENSION, Image.SCALE_SMOOTH);
    }

    @Override
    public void update(Observable o, Object arg) {
        // test
        executeMove((Move) arg);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame testFrame = new JFrame("BoardPanel Test");
        //testFrame.setPreferredSize(new Dimension(600, 600));
        BoardPanel boardPanel = new BoardPanel(new GameModel());
        testFrame.add(boardPanel);
        testFrame.pack();
        testFrame.setVisible(true);

    }
}
