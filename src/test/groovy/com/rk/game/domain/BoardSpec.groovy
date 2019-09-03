package com.rk.game.domain


import com.rk.game.domain.exception.IllegalBoardSizeException
import com.rk.game.domain.exception.IllegalCoordinateException
import com.rk.game.infrastructure.CoordinateStringParser
import io.micronaut.http.HttpStatus
import spock.lang.Specification
import spock.lang.Unroll

import static java.util.stream.Collectors.toList

class BoardSpec extends Specification {
    private String coordLoopInput =
            """
55 14 25 52 21
44 31 11 53 43
24 13 45 12 34
42 22 43 32 41
51 23 33 54 11
"""

    def "Should create board when coordinates are correct"() {
        given: "There are coordinates"
            def coords = (1..25).stream().map({ i -> new BoardCoordinate(i, i, new Coordinate(i, i)) }).collect(toList())
        when: "Board is created"
            def board = new Board(coords)
        then: "No exception is thrown"
            board != null
    }

    @Unroll
    def "Should not create board when coordinates are incorrect (size=#boardSize)"() {
        given: "There are coordinates"
            def coords = (1..boardSize).stream().map({ i -> new BoardCoordinate(i, i, new Coordinate(i, i)) }).collect(toList())
        when: "Board is being created"
            def board = new Board(coords)
        then: "Exception is thrown"
            IllegalBoardSizeException illegalBoardSizeException = thrown()
            illegalBoardSizeException != null
        where:
            boardSize << [24, 26]
    }

    def "Should find treasure within 9 steps"() {
        given: "There is a board"
            def gameBoard = new Board(new CoordinateStringParser().generateCoordinates())
        when: "Game is started"
            def gameResult = gameBoard.search(new Coordinate(1, 1))
        then: "Should generate 9 trails"
            gameResult.getTrails().size() == 9
        and: "First and last trail should be correct"
            gameResult.getTrails().get(0) == new Coordinate(1, 1)
            gameResult.getTrails().get(8) == new Coordinate(4, 3)
    }

    def "Should throw error if coordinate not found"() {
        given: "There is a board"
            def gameBoard = new Board(new CoordinateStringParser().generateCoordinates())
        when: "Game is started"
            def gameResult = gameBoard.search(new Coordinate(0, 1))
        then: "Exception is raised"
            IllegalCoordinateException ex = thrown()
            ex.getStatusCode() == HttpStatus.BAD_REQUEST.getCode()
    }


    def "Should lost game when go to loop"() {
        given: "There is a board"
            def gameBoard = new Board(new CoordinateStringParser().generateFromString(coordLoopInput))
        when: "Game is started"
            def gameResult = gameBoard.search(new Coordinate(1, 1))
        then: "Game is lost"
            gameResult.getTrails() == null
            gameResult.getMessage() == "NO TREASURE"
    }
}
