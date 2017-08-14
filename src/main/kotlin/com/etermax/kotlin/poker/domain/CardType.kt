package com.etermax.kotlin.poker.domain


enum class CardType {
    DIAMONDS, SPIDES, CLOVERS, HEARTS;

    companion object Representation{
        val values: HashMap<String, CardType> = hashMapOf("D" to DIAMONDS, "S" to SPIDES, "C" to CLOVERS,"H" to HEARTS)
    }

}