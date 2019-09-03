package com.rk.game.infrastructure

import com.rk.game.domain.BoardCoordinate
import com.rk.game.domain.Coordinate
import spock.lang.Specification

class CoordinateStringParserSpec extends Specification {
    private String coordInput =
            """
55 14 25 52 21
44 31 11 53 43
24 13 45 12 34
42 22 43 32 41
51 23 33 54 15
"""

    def "Should create correct boardCoords"() {
        when: "Coords are generated"
            def coords = new CoordinateStringParser().generateFromString(coordInput)
        then: "Coords size is correct"
            coords.size() == 25
        and: "First and last elements are correct"
            coords.get(0) == new BoardCoordinate(1, 1, new Coordinate(5, 5))
            coords.get(24) == new BoardCoordinate(5, 5, new Coordinate(1, 5))
    }

}
