package com.etermax.kotlin.poker

import com.etermax.kotlin.poker.rest.representation.GameReferee
import com.etermax.kotlin.poker.rest.resources.PokerResource
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.dropwizard.Application
import io.dropwizard.jersey.setup.JerseyEnvironment
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment

class PokerApplication : Application<PokerApplicationConfiguration>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            PokerApplication().run(*args)
        }
    }

    override fun initialize(bootstrap: Bootstrap<PokerApplicationConfiguration>) {
        super.initialize(bootstrap)
        bootstrap.objectMapper.registerKotlinModule()
    }

    override fun run(configuration: PokerApplicationConfiguration, environment: Environment) {
        configureEndpoints(environment.jersey())
    }


    fun configureEndpoints(jersey: JerseyEnvironment) {
        jersey.register(PokerResource(GameReferee("poker.txt")))
    }
}