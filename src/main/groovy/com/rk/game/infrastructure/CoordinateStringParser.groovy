package com.rk.game.infrastructure

import com.rk.game.domain.BoardCoordinate
import com.rk.game.domain.Coordinate
import com.rk.game.domain.CoordinateSupplier

import javax.inject.Singleton

@Singleton
class CoordinateStringParser implements CoordinateSupplier {
    private static final String coordInput =
            """
55 14 25 52 21
44 31 11 53 43
24 13 45 12 34
42 22 43 32 41
51 23 33 54 15
"""

    List<BoardCoordinate> generateFromString(String coordString) {
        List<BoardCoordinate> result = new ArrayList<>()
        coordString = coordString.trim()
        def coordRowList = coordString.split("\n").toList()

        for (int row = 1; row <= coordRowList.size(); row++) {
            def cellList = coordRowList.get(row - 1).split(" ").toList()
            for (int col = 1; col <= cellList.size(); col++) {
                result.add(new BoardCoordinate(row, col, createBoardCoordCell(cellList.get(col - 1))))
            }
        }
        return result
    }

    static private Coordinate createBoardCoordCell(String cell) {
        return new Coordinate(Character.getNumericValue(cell.charAt(0)), Character.getNumericValue(cell.charAt(1)))
    }

    @Override
    List<BoardCoordinate> generateCoordinates() {
        generateFromString(coordInput)
    }
}
