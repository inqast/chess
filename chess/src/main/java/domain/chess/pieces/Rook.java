package domain.chess.pieces;

import java.util.HashSet;

import domain.chess.ChessBoard;
import domain.chess.ChessPiece;
import domain.chess.Color;
import domain.chess.Point;
import utils.Moves;

public class Rook extends ChessPiece {
    
    public Rook(Color color) {
        super(color, "R");
    }

    @Override
    public HashSet<Point> getAvailableMoves(ChessBoard chessBoard, Point currentPosition) {
        HashSet<Point> availableMoves = new HashSet<>();
        
        availableMoves.addAll(Moves.getVertical(chessBoard, currentPosition));
        availableMoves.addAll(Moves.getHorizontal(chessBoard, currentPosition));

        return availableMoves;
    }
}
