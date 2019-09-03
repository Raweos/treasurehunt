package com.rk.game.infrastructure.rest

import com.rk.game.domain.application.TreasureGameService
import com.rk.game.domain.dto.GameResult
import com.rk.game.domain.exception.GameException
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Error
import io.micronaut.http.annotation.Get

//TODO: should return rest dto instead of domain dto
@Controller("/api/v1/game")
class TreasureHuntController {

    private final TreasureGameService treasureGameService

    TreasureHuntController(TreasureGameService treasureGameService) {
        this.treasureGameService = treasureGameService
    }

    @Get("/start/{row}/{col}")
    GameResult start(int row, int col) {
        return treasureGameService.search(row, col)
    }

    @Error(exception = GameException)
    HttpResponse handleError(GameException ex) {
        ex.printStackTrace() //logger should be used here
        return HttpResponse.status(HttpStatus.valueOf(ex.getStatusCode())).body(ex.getMessage())
    }

    @Error(exception = Exception)
    HttpResponse handleUnknownError(Exception ex) {
        ex.printStackTrace() //logger should be used here
        return HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknown error occurred")
    }
}
