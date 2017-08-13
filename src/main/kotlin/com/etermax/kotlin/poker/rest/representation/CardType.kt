package com.etermax.kotlin.poker.rest.representation


enum class CardType {
    DIAMONDS, SPIDES, CLOVERS, HEARTS;

    companion object Representation{
        val map: HashMap<String, CardType> = hashMapOf("D" to DIAMONDS, "S" to SPIDES, "C" to CLOVERS,"H" to HEARTS)
    }

}