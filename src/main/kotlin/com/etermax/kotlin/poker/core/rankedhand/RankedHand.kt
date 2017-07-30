package com.etermax.kotlin.poker.core.rankedhand

import com.etermax.kotlin.poker.core.CardRank

class RankedHand internal constructor(val rank: HandRank, rankCalculatorFunction: () -> CardRank) {

    val value = rankCalculatorFunction.invoke()

    companion object Factory : RankedHandFactory()

    fun isGraterThan(rankedHand: RankedHand): Boolean {
        return rank.isGreaterThan(rankedHand.rank)
    }
}