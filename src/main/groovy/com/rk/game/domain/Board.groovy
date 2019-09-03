package com.rk.game.domain

import com.rk.game.domain.dto.GameResult
import com.rk.game.domain.exception.IllegalBoardSizeException
import com.rk.game.domain.exception.IllegalCoordinateException
import io.micronaut.http.HttpStatus

import static java.lang.String.format

class Board {
    private final static int BOARD_SIZE = 25
    private final static int STEP_LIMIT = BOARD_SIZE
    private final List<BoardCoordinate> fields
    private List<Coordinate> trails = []

    Board(List<BoardCoordinate> fields) {
        if (fields.size() != BOARD_SIZE) {
            throw new IllegalBoardSizeException(format("Incorrect board size, should be %s", BOARD_SIZE),
                    HttpStatus.BAD_REQUEST.getCode())
        }
        this.fields = fields
    }

    GameResult search(Coordinate startPoint) {
        BoardCoordinate startCoordinate = findField(startPoint)
        trails.add(startPoint)
        if (startCoordinate.isTreasure()) {
            return new GameResult("TREASURE FOUND", trails)
        } else {
            if (trails.size() < STEP_LIMIT) {
                search(startCoordinate.getHint())
            } else {
                return new GameResult("NO TREASURE", null)
            }
        }
    }

    BoardCoordinate findField(Coordinate coordinate) {
        return fields.stream()
                .filter({ field -> field.matchCoordinate(coordinate) })
                .findFirst()
                .orElseThrow({ ->
                    new IllegalCoordinateException("Coord not found",
                            HttpStatus.BAD_REQUEST.getCode())
                })
    }

}
