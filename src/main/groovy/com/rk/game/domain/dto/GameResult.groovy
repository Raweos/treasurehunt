package com.rk.game.domain.dto

import com.rk.game.domain.Coordinate

class GameResult {
    private String message
    private List<Coordinate> trails

    GameResult(String message, List<Coordinate> trails) {
        this.message = message
        this.trails = trails
    }

    String getMessage() {
        return message
    }

    List<Coordinate> getTrails() {
        return trails
    }
}
