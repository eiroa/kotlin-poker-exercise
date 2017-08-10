package com.etermax.kotlin.poker.rest.representation

/**
 * Created by eiroa on 8/8/17.
 */
object CardParser {
    fun parseValue(value: String): Int {
        when (value) {
            "T" -> return 10
            "J" -> return 11
            "Q" -> return 12
            "K" -> return 13
            "A" -> return 14
            else -> return value.toInt()
        }
    }

    fun parseType(value: String): CardType {
        return CardType.map.get(value)!!
    }

    fun listOfCardsRepresentationToListOfCards(strings: List<String>): MutableList<Card> {
        val cardsResult = mutableListOf<Card>()
        strings.forEach { value -> cardsResult.add(Card(value)) }
        return cardsResult
    }

    fun stringToListOfPairStrings(values: String): List<String> {
        when (values) {
            "" -> return emptyList<String>()
            else -> return listOf(values.take(1) + values.take(2).drop(1)) + stringToListOfPairStrings(values.drop(2))
        }
    }

}