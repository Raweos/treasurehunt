package com.rk.game.domain

class BoardCoordinate {
    private int row
    private int col
    private Coordinate hint

    BoardCoordinate(int row, int col, Coordinate hint) {
        this.row = row
        this.col = col
        this.hint = hint
    }

    boolean isTreasure() {
        return hint.getCol() == this.col && hint.getRow() == this.row
    }

    Coordinate getHint() {
        return hint
    }

    boolean matchCoordinate(Coordinate coordinate) {
        return this.col == coordinate.getCol() && this.row == coordinate.getRow()
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (!(o instanceof BoardCoordinate)) return false

        BoardCoordinate that = (BoardCoordinate) o

        if (col != that.col) return false
        if (row != that.row) return false
        if (hint != that.hint) return false

        return true
    }

    int hashCode() {
        int result
        result = row
        result = 31 * result + col
        result = 31 * result + (hint != null ? hint.hashCode() : 0)
        return result
    }
}
