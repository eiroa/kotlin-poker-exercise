package com.etermax.kotlin.poker.rest.representation

import com.etermax.kotlin.poker.domain.CardParser
import com.etermax.kotlin.poker.domain.Hand
import com.etermax.kotlin.poker.domain.Player
import com.etermax.kotlin.poker.domain.Round
import com.fasterxml.jackson.annotation.JsonProperty


class GameReferee {
    val inputFile: String
    val fileLines: List<String>
    val rounds: List<Round>
    @JsonProperty("p1_victories") val p1Victories: Int
    @JsonProperty("p2_victories") val p2Victories: Int
    @JsonProperty("winner") val finalWinner: Player

    constructor(inputFile: String) {
        this.inputFile = inputFile
        this.fileLines = Thread.currentThread().contextClassLoader.getResourceAsStream("poker.txt").reader().readLines()
        var cardList = fileLines.map { line -> CardParser.stringToListOfCards(line.replace("\\s".toRegex(), "")) }
        this.rounds = cardList.map { list -> Round(Hand(list.take(5), Player.P1), Hand(list.takeLast(5), Player.P2)) }
        p1Victories = getVictoriesForPlayer(Player.P1)
        p2Victories = getVictoriesForPlayer(Player.P2)
        this.finalWinner = if (p1Victories == p2Victories) Player.NONE else if (p1Victories > p2Victories) Player.P1 else Player.P2
    }


    fun getVictoriesForPlayer(player: Player): Int {
        return rounds.filter { round -> round.determineWinner() == player }.size
    }


}