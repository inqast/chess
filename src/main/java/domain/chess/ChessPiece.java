package domain.chess;

import java.util.ArrayList;
import java.util.Set;

public abstract class ChessPiece {
    protected Color color;
    protected String letter;
    protected boolean check = true;

    protected ChessPiece(Color color, String letter) {
        this.color = color;
        this.letter = letter;
    }

    protected String getColorLetter() {
        if (color == Color.WHITE) {
            return  "w";
        } else {
            return "b";
        }
    }

    protected boolean isMoveAvailable(ArrayList<Point> availableMoves, Point currentPosition) {
        for (Point p : availableMoves) {
            if (p.equals(currentPosition)) {
                return true;
            }
        }

        return false;
    }

    public void markAsMoved() {
        this.check = false;    
    }

    public boolean isMoved() {
        return !check;    
    }

    public Color getColor() {
        return color;
    }

    public String getSymbol() {
        return this.letter + getColorLetter();
    }

    public boolean canMoveToPosition(ChessBoard chessBoard, Point currentPosition, Point targetPosition) {
        if (!targetPosition.isValid()
        || (targetPosition.equals(currentPosition))) {
            return false;
        }

        Set<Point> availableMoves = getAvailableMoves(chessBoard, currentPosition);

        boolean isMoveAvailable = availableMoves.contains(targetPosition);

        if (check && isMoveAvailable) {
            markAsMoved();
        }

        return isMoveAvailable;
    }

    public abstract Set<Point> getAvailableMoves(ChessBoard chessBoard, Point currentPosition);
}