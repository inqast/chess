package domain.chess.pieces;

import java.util.HashSet;
import java.util.List;

import domain.chess.ChessBoard;
import domain.chess.ChessPiece;
import domain.chess.Color;
import domain.chess.Point;
import utils.Moves;

public class Pawn extends ChessPiece {
    
    public Pawn(Color color) {
        super(color, "P");
    }

    @Override
    public HashSet<Point> getAvailableMoves(ChessBoard chessBoard, Point currentPosition) {
        HashSet<Point> availableMoves = new HashSet<>();

        availableMoves.addAll(getAvailableVerticalMoves(chessBoard, currentPosition));
        availableMoves.addAll(getAvailableDiagonalMoves(chessBoard, currentPosition));

        return availableMoves;
    }

    private HashSet<Point> getAvailableVerticalMoves(ChessBoard chessBoard, Point currentPosition) {
        HashSet<Point> availableMoves = new HashSet<>();

        List<Point> vertical;
        if (color == Color.WHITE) {
            vertical = Moves.getUpperVertical(chessBoard, currentPosition);
        } else {
            vertical = Moves.getLowerVertical(chessBoard, currentPosition);
        }
        
        if (!vertical.isEmpty()) {
            availableMoves.add(vertical.get(0));
        }
        if (!isMoved() && vertical.size() > 1) {
            availableMoves.add(vertical.get(1));
        }

        return availableMoves;
    }

    private HashSet<Point> getAvailableDiagonalMoves(ChessBoard chessBoard, Point currentPosition) {
        HashSet<Point> availableMoves = new HashSet<>();

        Point left;
        Point right;

        if (color == Color.WHITE) {
            left = new Point(currentPosition.getLine()+1, currentPosition.getColumn()-1);
            right = new Point(currentPosition.getLine()+1, currentPosition.getColumn()+1);
        } else {
            left = new Point(currentPosition.getLine()-1, currentPosition.getColumn()-1);
            right = new Point(currentPosition.getLine()-1, currentPosition.getColumn()+1);
        }

        if (chessBoard.isTaken(left) && chessBoard.getChessPiece(left).getColor() != this.getColor()) {
            availableMoves.add(left);
        }
        if (chessBoard.isTaken(right) && chessBoard.getChessPiece(right).getColor() != this.getColor()) {
            availableMoves.add(right);
        }

        return availableMoves;
    }
}
