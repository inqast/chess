package domain.chess;

import java.util.Objects;

public class Point {
    int line;
    int column;

    public Point(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public boolean isValid() {
        return checkPos(line) && checkPos(column);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point p = (Point) o;
        return line == p.line && column == p.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(line, column);
    }

    private boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}
