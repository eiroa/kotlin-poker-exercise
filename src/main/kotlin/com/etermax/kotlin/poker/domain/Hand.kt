package com.etermax.kotlin.poker.domain

import javax.management.Query.not


class Hand {
    val cards: List<Card>
    val player: Player
    var result: HandResult = HandResult.HIGHEST_CARD

    constructor(cards: List<Card>, player: Player) {
        this.cards = cards
        this.player = player
        result = this.calculateResult(cards)
    }

    fun calculateResult(cards: List<Card>): HandResult {
        when {
            HandResult.isStraight(cards) -> return calculateStraightType(cards)
            HandResult.isPoker(cards) -> return HandResult.POKER
            HandResult.isFull(cards) -> return HandResult.FULL
            HandResult.areSameType(cards) -> return HandResult.FLUSH
            HandResult.isThreeOfAKind(cards) -> return HandResult.THREE_OF_A_KIND
            HandResult.isTwoPairs(cards) -> return HandResult.TWO_PAIRS
            HandResult.isOnePair(cards) -> return HandResult.ONE_PAIR
            else -> return HandResult.HIGHEST_CARD
        }
    }


    fun calculateStraightType(cards: List<Card>):HandResult{
        when{
            HandResult.areSameType(cards) && cards.sumBy { card -> card.value  } == 60 -> return HandResult.ROYAL_FLUSH
            HandResult.areSameType(cards) && !(cards.sumBy { card -> card.value  } == 60) -> return HandResult.STRAIGHT_FLUSH
            else -> return HandResult.STRAIGHT
        }
    }
}