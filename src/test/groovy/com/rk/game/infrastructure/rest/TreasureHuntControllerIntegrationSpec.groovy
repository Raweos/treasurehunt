package com.rk.game.infrastructure.rest

import groovy.json.JsonSlurper
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class TreasureHuntControllerIntegrationSpec extends Specification {
    @Inject
    @Client("/api/v1/game/start")
    private RxHttpClient client

    def "Should return correct response"() {
        given: "There are coordinates"
            int row = 1
            int col = 1
            def request = HttpRequest.GET("/$row/$col")
        when: "Request is sent"
            def response = client.toBlocking().retrieve(request)
        then: "Treasure found"
            def jsonResponse = new JsonSlurper().parseText(response)
            jsonResponse["message"] == "TREASURE FOUND"
    }

}
