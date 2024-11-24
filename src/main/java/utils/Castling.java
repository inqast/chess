package utils;

import java.util.HashSet;

import domain.chess.ChessBoard;
import domain.chess.ChessPiece;
import domain.chess.Point;

public abstract class Castling {
    public static boolean isShortCastlingAvailable(ChessBoard chessBoard, Point kingPosition, Point rookPosition) {
        return !isMoved(chessBoard, kingPosition, rookPosition) &&
        isShortPathFree(chessBoard, kingPosition.getLine()) &&
        isShortPathSafe(chessBoard, kingPosition.getLine());
    }

    public static boolean isLongCastlingAvailable(ChessBoard chessBoard, Point kingPosition, Point rookPosition) {
        return !isMoved(chessBoard, kingPosition, rookPosition) &&
        isLongPathFree(chessBoard, kingPosition.getLine()) &&
        isLongPathSafe(chessBoard, kingPosition.getLine());
    }

    public static void processShortCastling(ChessBoard chessBoard, Point kingPosition, Point rookPosition) {
        processCastling(chessBoard, kingPosition, rookPosition, 
        new Point(kingPosition.getLine(), 6), 
        new Point(kingPosition.getLine(), 5)
        );
    }

    public static void processLongCastling(ChessBoard chessBoard, Point kingPosition, Point rookPosition) {
        processCastling(chessBoard, kingPosition, rookPosition, 
        new Point(kingPosition.getLine(), 2), 
        new Point(kingPosition.getLine(), 3)
        );
    }

    private static boolean isMoved(ChessBoard chessBoard, Point kingPosition, Point rookPosition) {
        ChessPiece king = chessBoard.getChessPiece(kingPosition);
        ChessPiece rook = chessBoard.getChessPiece(rookPosition);


        return king == null || king.isMoved() || rook == null || rook.isMoved();
    }

    private static boolean isShortPathFree(ChessBoard chessBoard, int line) {
        return isPathFree(chessBoard, 5, 7, line);
    }

    private static boolean isShortPathSafe(ChessBoard chessBoard, int line) {
        return isPathSafe(chessBoard, 4, 7, line);
    }

    private static boolean isLongPathFree(ChessBoard chessBoard, int line) {
        return isPathFree(chessBoard, 1, 4, line);
    }

    private static boolean isLongPathSafe(ChessBoard chessBoard, int line) {
        return isPathSafe(chessBoard, 2, 4, line);
    }

    private static boolean isPathFree(ChessBoard chessBoard, int form , int to, int line) {
        for (int column = form; column < to; column++) {
            Point currentPosition = new Point(line, column);

            if (chessBoard.isTaken(currentPosition)) {
                return false;
            }
        }

        return true;
    }

    private static boolean isPathSafe(ChessBoard chessBoard, int form , int to, int line) {
        HashSet<Point> attakedPoints = chessBoard.getAttakedPoints(chessBoard.nowPlayerColor());

        for (int column = form; column < to; column++) {
            Point currentPosition = new Point(line, column);

            if (attakedPoints.contains(currentPosition)) {
                return false;
            }
        }

        return true;
    }

    private static void processCastling(ChessBoard chessBoard, Point kingFrom, Point rookFrom, Point kingTo, Point rookTo) {
        ChessPiece king = chessBoard.getChessPiece(kingFrom);
        ChessPiece rook = chessBoard.getChessPiece(rookFrom);

        king.markAsMoved();
        rook.markAsMoved();

        chessBoard.setChessPiece(kingTo, king);
        chessBoard.setChessPiece(rookTo, rook);

        chessBoard.setChessPiece(kingFrom, null);
        chessBoard.setChessPiece(rookFrom, null);
    }
}
