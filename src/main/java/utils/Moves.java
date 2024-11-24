package utils;

import java.util.ArrayList;
import java.util.List;

import domain.chess.ChessBoard;
import domain.chess.Color;
import domain.chess.Point;

public abstract class Moves {
    public static List<Point> getVertical(ChessBoard chessBoard, Point currentPosition) {
        ArrayList<Point> availableMoves = new ArrayList<>();

        availableMoves.addAll(getUpperVertical(chessBoard,currentPosition));
        availableMoves.addAll(getLowerVertical(chessBoard,currentPosition));

        return availableMoves;
    }

    public static List<Point> getUpperVertical(ChessBoard chessBoard, Point currentPosition) {
        ArrayList<Point> availableMoves = new ArrayList<>();
        Color currentColor = chessBoard.getChessPiece(currentPosition).getColor();

        for (int i = currentPosition.getLine()+1; i < 8; i++) {
            Point availableMove = new Point(i, currentPosition.getColumn());
            if (!availableMove.isValid()) {
                continue;
            }
            
            if (chessBoard.isTaken(availableMove)) {
                if (chessBoard.getChessPiece(availableMove).getColor() != currentColor) {
                    availableMoves.add(availableMove);
                }

                break;
            }

            
            availableMoves.add(availableMove);
        }

        return availableMoves;
    }

    public static List<Point> getLowerVertical(ChessBoard chessBoard, Point currentPosition) {
        ArrayList<Point> availableMoves = new ArrayList<>();
        Color currentColor = chessBoard.getChessPiece(currentPosition).getColor();

        for (int i = currentPosition.getLine()-1; i >= 0; i--) {
            Point availableMove = new Point(i, currentPosition.getColumn());
            if (!availableMove.isValid()) {
                continue;
            }

            if (chessBoard.isTaken(availableMove)) {
                if (chessBoard.getChessPiece(availableMove).getColor() != currentColor) {
                    availableMoves.add(availableMove);
                }

                break;
            }

            
            availableMoves.add(availableMove);
        }

        return availableMoves;
    }

    public static List<Point> getHorizontal(ChessBoard chessBoard, Point currentPosition) {
        ArrayList<Point> availableMoves = new ArrayList<>();
        Color currentColor = chessBoard.getChessPiece(currentPosition).getColor();

        for (int i = currentPosition.getColumn()+1; i < 8; i++) {
            Point availableMove = new Point(currentPosition.getLine(), i);
            if (!availableMove.isValid()) {
                continue;
            }

            if (chessBoard.isTaken(availableMove)) {
                if (chessBoard.getChessPiece(availableMove).getColor() != currentColor) {
                    availableMoves.add(availableMove);
                }

                break;
            }

            
            availableMoves.add(availableMove);
        }

        for (int i = currentPosition.getColumn()-1; i >= 0; i--) {
            Point availableMove = new Point(currentPosition.getLine(), i);
            if (!availableMove.isValid()) {
                continue;
            }

            if (chessBoard.isTaken(availableMove)) {
                if (chessBoard.getChessPiece(availableMove).getColor() != currentColor) {
                    availableMoves.add(availableMove);
                }

                break;
            }

            
            availableMoves.add(availableMove);
        }

        return availableMoves;
    }

    public static List<Point> getUpperDiagonals(ChessBoard chessBoard, Point currentPosition) {
        ArrayList<Point> availableMoves = new ArrayList<>();
        Color currentColor = chessBoard.getChessPiece(currentPosition).getColor();

        int left = currentPosition.getColumn()-1;
        boolean isLeftTaken = false;

        int right = currentPosition.getColumn()+1;
        boolean isRightTaken = false;

        for (int i = currentPosition.getLine()+1; i < 8; i++) {
            if (left >= 0 && !isLeftTaken) {
                Point availableMove = new Point(i, left);

                if (!availableMove.isValid()) {
                    continue;
                }
                
                isLeftTaken = chessBoard.isTaken(availableMove);

                if (!isLeftTaken || (chessBoard.getChessPiece(availableMove).getColor() != currentColor)) {
                    availableMoves.add(availableMove);
                }

                left--;
            }

            if (right < 8  && !isRightTaken) {
                Point availableMove = new Point(i, right);

                if (!availableMove.isValid()) {
                    continue;
                }

                isRightTaken = chessBoard.isTaken(availableMove);

                if (!isRightTaken || (chessBoard.getChessPiece(availableMove).getColor() != currentColor)) {
                    availableMoves.add(availableMove);
                }

                right++;
            }
        }

        return availableMoves;
    }

    public static List<Point> getLowerDiagonals(ChessBoard chessBoard, Point currentPosition) {
        ArrayList<Point> availableMoves = new ArrayList<>();
        Color currentColor = chessBoard.getChessPiece(currentPosition).getColor();

        int left = currentPosition.getColumn()-1;
        boolean isLeftTaken = false;

        int right = currentPosition.getColumn()+1;
        boolean isRightTaken = false;

        for (int i = currentPosition.getLine()-1; i >= 0; i--) {
            if (left >= 0 && !isLeftTaken) {
                Point availableMove = new Point(i, left);

                if (!availableMove.isValid()) {
                    continue;
                }
                
                isLeftTaken = chessBoard.isTaken(availableMove);

                if (!isLeftTaken || (chessBoard.getChessPiece(availableMove).getColor() != currentColor)) {
                    availableMoves.add(availableMove);
                }

                left--;
            }

            if (right < 8  && !isRightTaken) {
                Point availableMove = new Point(i, right);

                 if (!availableMove.isValid()) {
                    continue;
                }

                isRightTaken = chessBoard.isTaken(availableMove);

                if (!isRightTaken || (chessBoard.getChessPiece(availableMove).getColor() != currentColor)) {
                    availableMoves.add(availableMove);
                }

                right++;
            }
        }

        return availableMoves;
    }
}
