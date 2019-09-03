package com.rk.game.domain.exception

class GameException extends RuntimeException {
    private final int statusCode

    GameException(String message, int statusCode) {
        super(message)
        this.statusCode = statusCode
    }

    int getStatusCode() {
        return statusCode
    }
}
