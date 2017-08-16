package com.etermax.kotlin.poker.rest.representation

import com.etermax.kotlin.poker.domain.Player
import com.fasterxml.jackson.annotation.JsonProperty


data class GameResult(@JsonProperty("p1_victories") val p1Victories:Int, @JsonProperty("p2_victories") val p2Victories:Int, @JsonProperty("winner")
val winner: Player) {
}