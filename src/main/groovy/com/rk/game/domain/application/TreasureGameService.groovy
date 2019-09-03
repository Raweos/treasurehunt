package com.rk.game.domain.application

import com.rk.game.domain.Board
import com.rk.game.domain.Coordinate
import com.rk.game.domain.CoordinateSupplier
import com.rk.game.domain.dto.GameResult

import javax.inject.Singleton

@Singleton
class TreasureGameService {
    private final CoordinateSupplier coordinateSupplier

    TreasureGameService(CoordinateSupplier coordinateSupplier) {
        this.coordinateSupplier = coordinateSupplier
    }

    GameResult search(int row, int col) {
        def board = new Board(coordinateSupplier.generateCoordinates())
        return board.search(new Coordinate(row, col))
    }
}
