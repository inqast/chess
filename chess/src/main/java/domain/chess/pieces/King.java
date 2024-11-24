package domain.chess.pieces;

import java.util.HashSet;
import java.util.Set;

import domain.chess.ChessBoard;
import domain.chess.ChessPiece;
import domain.chess.Color;
import domain.chess.Point;

public class King extends ChessPiece {
    
    public King(Color color) {
        super(color, "K");
    }

    @Override
    public HashSet<Point> getAvailableMoves(ChessBoard chessBoard, Point currentPosition) {

        HashSet<Point> availableMoves = new HashSet<>();
        for (int i = currentPosition.getLine()-1; i <= currentPosition.getLine()+2; i++) {
            for (int j = currentPosition.getColumn()-1; j <= currentPosition.getColumn()+2; j++) {
                Point move = new Point(i, j);

                if (move.isValid() 
                && !move.equals(currentPosition)
                && (!chessBoard.isTaken(move) || chessBoard.getChessPiece(move).getColor() != this.color)) {
                    availableMoves.add(move);
                }
            }
        }

        return availableMoves;
    }

    public boolean isUnderAttack(Set<Point> attackedPoints, Point currentPosition) {
        return attackedPoints.contains(currentPosition);
    }
}
