package com.rk.game.domain

class Coordinate {
    private int row
    private int col

    Coordinate(int row, int col) {
        this.row = row
        this.col = col
    }

    int getRow() {
        return row
    }

    int getCol() {
        return col
    }

    @Override
    String toString() {
        return "$row $col"
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Coordinate that = (Coordinate) o

        if (col != that.col) return false
        if (row != that.row) return false

        return true
    }

    int hashCode() {
        int result
        result = row
        result = 31 * result + col
        return result
    }
}
