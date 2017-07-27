package com.etermax.kotlin.poker.core

class Hand(card: Card, card1: Card, card2: Card, card3: Card, card4: Card) {

    private val rankedHand: RankedHand = findRankedHand()

    private fun findRankedHand(): RankedHand {
        //TODO: evualuar las cartas y ver que combo contiene
        return RankedHand()
    }

    fun beats(hand: Hand): Boolean {
        return this.rankedHand.isGraterThan(hand.rankedHand)
    }

}

