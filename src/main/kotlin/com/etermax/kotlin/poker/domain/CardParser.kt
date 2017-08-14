package com.etermax.kotlin.poker.domain


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
        return CardType.values.get(value)!!
    }

    fun stringToListOfCards(values: String): List<Card> {
        when (values) {
            "" -> return emptyList<Card>()
            else -> return listOf(Card(values.take(1) + values.take(2).drop(1))) + stringToListOfCards(values.drop(2))
        }
    }

}