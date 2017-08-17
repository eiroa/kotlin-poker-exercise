package com.etermax.kotlin.poker.domain


class GameReferee {
    val inputFile: String
    val fileLines: List<String>
    val rounds: List<Round>
    val p1Victories: Int
    val p2Victories: Int
    val finalWinner: Player
    val totalPointsP1: Int
    val totalPointsP2: Int
    val totalWinnerPointsP1: Int
    val totalWinnerPoinstP2: Int
    val orderOfAppeareaceOfP1Cads: List<String>
    val orderOfAppeareaceOfP2Cads: List<String>

    constructor(inputFile: String) {
        this.inputFile = inputFile
        this.fileLines = Thread.currentThread().contextClassLoader.getResourceAsStream("poker.txt").reader().readLines()
        var cardList = fileLines.map { line -> CardParser.stringToListOfCards(line.replace("\\s".toRegex(), "")) }
        this.rounds = cardList.map { list -> Round(Hand(list.take(5), Player.P1), Hand(list.takeLast(5), Player.P2)) }
        p1Victories = getVictoriesForPlayer(Player.P1)
        p2Victories = getVictoriesForPlayer(Player.P2)
        this.finalWinner = if (p1Victories == p2Victories) Player.NONE else if (p1Victories > p2Victories) Player.P1 else Player.P2
        this.totalPointsP1 = getTotalPointsForPlayer(Player.P1)
        this.totalPointsP2 = getTotalPointsForPlayer(Player.P2)
        this.totalWinnerPointsP1 = getTotalPointsOfWinnerHandsForPlayer(Player.P1)
        this.totalWinnerPoinstP2= getTotalPointsOfWinnerHandsForPlayer(Player.P2)
        this.orderOfAppeareaceOfP1Cads = getOrderOfAppeareanceCardsForPlayer(Player.P1)
        this.orderOfAppeareaceOfP2Cads = getOrderOfAppeareanceCardsForPlayer(Player.P2)
    }


    fun getVictoriesForPlayer(player: Player): Int {
        return rounds.filter { round -> round.determineWinner() == player }.size
    }

    fun getTotalPointsForPlayer(player:Player):Int{
        return rounds.map { round -> round.getHandForPlayer(player).cards.sumBy { card ->  card.value}}.sum()
    }

    fun getTotalPointsOfWinnerHandsForPlayer(player:Player):Int{
        return rounds.filter { round -> round.determineWinner() == player }.map{ round -> round.getHandForPlayer(player).cards.sumBy { card ->  card.value}}.sum()
    }

    fun getOrderOfAppeareanceCardsForPlayer(player:Player):List<String>{
        return rounds.map { round -> round.getHandForPlayer(player)}
                .flatMap { rounds -> rounds.cards }
                .groupBy { card -> card.getValueAsString() }.entries
                .map { set -> Pair(set.key,set.value.size) }
                .sortedBy { pair -> pair.second }.reversed()
                .map { pair -> pair.first + ", " + ""+pair.second }
    }



}