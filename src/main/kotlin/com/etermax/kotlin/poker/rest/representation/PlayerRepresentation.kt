package com.etermax.kotlin.poker.rest.representation

import com.etermax.kotlin.poker.domain.Player
import com.fasterxml.jackson.annotation.JsonProperty


data class PlayerRepresentation(@JsonProperty("winner") val player: Player)