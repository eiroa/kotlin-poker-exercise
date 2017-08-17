package com.etermax.kotlin.poker.rest.representation

import com.etermax.kotlin.poker.domain.Player
import com.fasterxml.jackson.annotation.JsonProperty


data class GameResult(
        @JsonProperty("p1_victories") val p1Victories: Int,
        @JsonProperty("p2_victories") val p2Victories: Int,
        @JsonProperty("winner") val winner: Player,
        @JsonProperty("p1_total_points") val p1TotalPoints: Int,
        @JsonProperty("p2_total_points") val p2TotalPoints: Int,
        @JsonProperty("p1_winner_hands_points") val p1TotalWinnerHandsPoints: Int,
        @JsonProperty("p2_winner_hands_points") val p2TotalWinnerHandsPoints: Int,
        @JsonProperty("p1_order_of_appearance_cards") val p1Cards: List<String>,
        @JsonProperty("p2_order_of_appearance_cards") val p2Cards: List<String>)