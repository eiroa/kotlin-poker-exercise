package com.etermax.kotlin.poker.core.rankedhand

enum class HandRank {
    HIGH_CARD,
    ONE_PAIR,
    TWO_PAIR,
    THREE_OF_A_KIND,
    STRAIGHT,
    FLUSH,
    FULL_HOUSE,
    FOUR_OF_A_KIND,
    STRAIGHT_FLUSH,
    ROYAL_FLUSH;

    fun isGreaterThan(handRank: HandRank): Boolean {
        return this.ordinal > handRank.ordinal
    }
}