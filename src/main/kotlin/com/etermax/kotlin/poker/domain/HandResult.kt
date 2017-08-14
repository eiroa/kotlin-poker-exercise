package com.etermax.kotlin.poker.domain


enum class HandResult() {
    ROYAL_FLUSH, STRAIGHT_FLUSH, POKER, FULL, FLUSH, STRAIGHT, THREE_OF_A_KIND, TWO_PAIRS, ONE_PAIR, HIGHEST_CARD;

    companion object Representation {
        val resultValues: HashMap<Int, HandResult> = hashMapOf(10 to ROYAL_FLUSH, 9 to STRAIGHT_FLUSH, 8 to POKER, 7 to FULL, 6 to FLUSH, 5 to
                STRAIGHT, 4 to THREE_OF_A_KIND, 3 to TWO_PAIRS, 2 to ONE_PAIR, 1 to HIGHEST_CARD)
    }
}