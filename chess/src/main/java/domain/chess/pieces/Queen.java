package domain.chess.pieces;

import java.util.HashSet;

import domain.chess.ChessBoard;
import domain.chess.ChessPiece;
import domain.chess.Color;
import domain.chess.Point;
import utils.Moves;

public class Queen extends ChessPiece {
    
    public Queen(Color color) {
        super(color, "Q");
    }

    @Override
    public HashSet<Point> getAvailableMoves(ChessBoard chessBoard, Point currentPosition) {
        HashSet<Point> availableMoves = new HashSet<>();
        
        availableMoves.addAll(Moves.getUpperDiagonals(chessBoard, currentPosition));
        availableMoves.addAll(Moves.getLowerDiagonals(chessBoard, currentPosition));
        availableMoves.addAll(Moves.getVertical(chessBoard, currentPosition));
        availableMoves.addAll(Moves.getHorizontal(chessBoard, currentPosition));

        return availableMoves;
    }
}
