package com.etermax.kotlin.poker.domain


class GameReferee {
    val inputFile: String
    val fileLines: List<String>
    val rounds: List<Round>
    val p1Victories: Int
    val p2Victories: Int
    val finalWinner: Player

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

    //fun getTotalPointsForPlayer(player:Player):Int{
    //    return rounds.filter { round -> round.player1Hand.player == player }.map { round -> round. }
    //}


}