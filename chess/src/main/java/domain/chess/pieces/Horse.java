package domain.chess.pieces;

import java.util.HashSet;

import domain.chess.ChessBoard;
import domain.chess.ChessPiece;
import domain.chess.Color;
import domain.chess.Point;

public class Horse extends ChessPiece {
    
    public Horse(Color color) {
        super(color, "H");
    }

    @Override
    public HashSet<Point> getAvailableMoves(ChessBoard chessBoard, Point currentPosition) {
        HashSet<Point> availableMoves = new HashSet<>();
        
        for (int i = currentPosition.getLine()-2; i <= currentPosition.getLine()+2; i++) {
            if (i == currentPosition.getLine()) {
                continue;
            }

            int range = 3 - Math.abs(currentPosition.getLine()-i);

            Point leftMove = new Point(i, currentPosition.getColumn()-range);
            Point rightMove = new Point(i, currentPosition.getColumn()+range);

            if (leftMove.isValid() && (!chessBoard.isTaken(leftMove) || chessBoard.getChessPiece(leftMove).getColor() != this.color)) {
                availableMoves.add(leftMove);
            }

            if (rightMove.isValid() && (!chessBoard.isTaken(rightMove) || chessBoard.getChessPiece(rightMove).getColor() != this.color)) {
                availableMoves.add(rightMove);
            }
        }

        return availableMoves;
    }
}
