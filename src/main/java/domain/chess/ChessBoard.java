package domain.chess;

import java.util.HashSet;

import utils.Castling;
import utils.CheckMate;

public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    Color nowPlayer;

    public ChessBoard(Color nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public Color nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        Point currentPoint = new Point(startLine, startColumn);
        Point targetPoint = new Point(endLine, endColumn);

        if (currentPoint.isValid() && isTaken(currentPoint)) {

            if (!nowPlayer.equals(getChessPiece(currentPoint).getColor())) return false;

            if (getChessPiece(currentPoint).canMoveToPosition(this, currentPoint, targetPoint)) {
                setChessPiece(targetPoint, getChessPiece(currentPoint)); // if piece can move, we moved a piece
                setChessPiece(currentPoint, null); // set null to previous cell
                switchPlayer();

                return true;
            } else return false;
        } else return false;
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");
        
        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        if (nowPlayer == Color.WHITE) {
            System.out.println("Player 1(White)");
        } else {
            System.out.println("Player 2(Black)");
        }
    }

    public HashSet<Point> getAttakedPoints(Color color) {
        HashSet<Point> attakedPoints = new HashSet<>();

        for (int line = 0; line < 8; line++) {
            for (int column = 0; column < 8; column++) {
                Point currentPosition = new Point(line, column);

                if (!isTaken(currentPosition) || getChessPiece(currentPosition).getColor() == color) {
                    continue;
                }

                attakedPoints.addAll(getChessPiece(currentPosition).getAvailableMoves(this, currentPosition));
            }
        }

        return attakedPoints;
    }

    public boolean isTaken(Point targetPoint) {        
        return getChessPiece(targetPoint) != null;
    }

    public ChessPiece getChessPiece(Point point) {
        if (!point.isValid()) {
            return null;
        }

        return board[point.getLine()][point.getColumn()];
    }

    public void setChessPiece(Point point, ChessPiece piece) {
        if (!point.isValid()) {
            return;
        }

        board[point.getLine()][point.getColumn()] = piece;
    }

    public boolean castling0() {
        Point kingPosition = getKingInitialPosition();
        Point rookPosition = new Point(kingPosition.getLine(), 0);

        if (!Castling.isLongCastlingAvailable(this, kingPosition, rookPosition)) {
            return false;
        }

        Castling.processLongCastling(this, kingPosition, rookPosition);
        switchPlayer();

        return true;
    }

    public boolean castling7() {
        Point kingPosition = getKingInitialPosition();
        Point rookPosition = new Point(kingPosition.getLine(), 7);

        if (!Castling.isShortCastlingAvailable(this, kingPosition, rookPosition)) {
            return false;
        }

        Castling.processShortCastling(this, kingPosition, rookPosition);
        switchPlayer();

        return true;
    }

    public Point getKingPosition(Color color) {
        String targetSymbol = "Kw";
        if (color == Color.BLACK) {
            targetSymbol = "Kb";
        }
        
        for (int line = 0; line < 8; line++) {
            for (int column = 0; column < 8; column++) {
                Point currentPosition = new Point(line, column);
                if (!isTaken(currentPosition)) {
                    continue;
                }

                ChessPiece piece = getChessPiece(currentPosition);

                if (piece.getSymbol().equals(targetSymbol)) {
                    return currentPosition;
                }
            }
        }
        
        return null;
    }

    public Color check() {
        return CheckMate.check(this);
    }

    public boolean mateForColor(Color color) {
        return CheckMate.mateForColor(this, color);
    }

    private Point getKingInitialPosition() {
        Point position =  new Point(0, 4);
        
        if (nowPlayer == Color.BLACK) {
            position =  new Point(7, 4);
        }
        
        return position;
    }

    private void switchPlayer() {
        if (nowPlayer == Color.BLACK) {
            nowPlayer = Color.WHITE;
        } else {
            nowPlayer = Color.BLACK;
        }
    }
}
