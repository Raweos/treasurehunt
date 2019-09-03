package com.rk.game.domain.exception

class IllegalCoordinateException extends GameException {
    IllegalCoordinateException(String message, int statusCode) {
        super(message, statusCode)
    }
}
