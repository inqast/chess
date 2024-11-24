package utils;

import java.util.HashSet;
import java.util.Set;

import domain.chess.ChessBoard;
import domain.chess.ChessPiece;
import domain.chess.Color;
import domain.chess.Point;
import domain.chess.pieces.King;

public abstract class CheckMate {
    public static Color check(ChessBoard chessBoard) {
        if (checkForColor(chessBoard, Color.WHITE)) {
            return Color.WHITE;
        }

        if (checkForColor(chessBoard, Color.BLACK)) {
            return Color.BLACK;
        }

        return null;
    }

    public static boolean mateForColor(ChessBoard chessBoard, Color color) {
        if (color == null) {
            return false;
        }

        Point kingPosition = chessBoard.getKingPosition(color);
        ChessPiece king = chessBoard.getChessPiece(kingPosition);

        Set<Point> availableMoves = king.getAvailableMoves(chessBoard, kingPosition);
        Set<Point> attakedPoints = chessBoard.getAttakedPoints(color);
        availableMoves = filterAttackedMoves(availableMoves, attakedPoints);

        return availableMoves.isEmpty();
    }

    private static boolean checkForColor(ChessBoard chessBoard, Color color) {
        Point kingPosition = chessBoard.getKingPosition(color);
        King king = (King) chessBoard.getChessPiece(kingPosition);

        return king.isUnderAttack(chessBoard.getAttakedPoints(color), kingPosition);
    }

    private static Set<Point> filterAttackedMoves(Set<Point> availableMoves, Set<Point> attakedPoints) {
        HashSet<Point> filteredMoves = new HashSet<>();

        for (Point p : availableMoves) {
            if (attakedPoints.contains(p)) {
                continue;
            }

            filteredMoves.add(p);
        }

        return filteredMoves;
    }

    private static Color invertColor(Color color) {
        if (color == Color.BLACK) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }
}
