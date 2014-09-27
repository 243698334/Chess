package com.kevinychen.chess.main.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class PieceDragAndDropListener implements MouseListener, MouseMotionListener {

    private boolean dragging;
    private char originFile;
    private int originRank;
    private int dragOffsetX;
    private int dragOffsetY;

    @Override
    public void mousePressed(MouseEvent e) {
        BoardPanel boardPanel = (BoardPanel) e.getSource();
        originFile = calculateFile(e);
        originRank = calculateRank(e);
        dragOffsetX = e.getPoint().x - boardPanel.SQUARE_DIMENSION * (calculateFile(e) - 'a');
        dragOffsetY = e.getPoint().y - boardPanel.SQUARE_DIMENSION * (8 - calculateRank(e));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (dragging) {
            BoardPanel boardPanel = (BoardPanel) e.getSource();
            boardPanel.submitMoveRequest(originFile, originRank, calculateFile(e), calculateRank(e));
        }
        dragging = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        dragging = true;
        BoardPanel boardPanel = (BoardPanel) e.getSource();
        boardPanel.executeDrag(originFile, originRank, e.getPoint().x - dragOffsetX, e.getPoint().y - dragOffsetY);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // nothing
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // nothing
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // nothing
    }

    private char calculateFile(MouseEvent e) {
        return (char) ('a' + e.getPoint().x / ((BoardPanel)e.getSource()).SQUARE_DIMENSION);
    }

    private int calculateRank(MouseEvent e) {
        return 8 - e.getPoint().y / ((BoardPanel)e.getSource()).SQUARE_DIMENSION;
    }

}