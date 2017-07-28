package com.etermax.kotlin.poker.core

class Hand(val cards: List<Card>) {

    private val rankedHand: RankedHand = findRankedHand()

    private fun findRankedHand(): RankedHand {
        //TODO: evualuar las cartas y ver que combo contiene
        return RankedHand(this)
    }

    fun beats(hand: Hand): Boolean {
        return this.rankedHand.isGraterThan(hand.rankedHand)
    }

}

