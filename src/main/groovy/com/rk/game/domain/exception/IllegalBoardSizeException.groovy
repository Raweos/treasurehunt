package com.rk.game.domain.exception

class IllegalBoardSizeException extends GameException {
    IllegalBoardSizeException(String message, int statusCode) {
        super(message, statusCode)
    }
}
