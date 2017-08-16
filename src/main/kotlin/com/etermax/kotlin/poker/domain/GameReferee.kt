package com.etermax.kotlin.poker.domain


class GameReferee {
    val inputFile:String
    var fileLines:List<String>
    var rounds:List<Round>

    constructor(inputFile:String) {
        this.inputFile = inputFile
        this.fileLines = Thread.currentThread().contextClassLoader.getResourceAsStream("poker.txt").reader().readLines()
        var cardList = fileLines.map{ line ->  CardParser.stringToListOfCards(line.replace("\\s".toRegex(), "")) }
        this.rounds =   cardList.map { list -> Round( Hand(list.take(5),Player.P1), Hand(list.takeLast(5),Player.P2)) }
    }


}