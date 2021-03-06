package com.etermax.kotlin.poker.domain


class Hand {
    val cards: List<Card>
    val player: Player
    var result: Pair<HandResult, List<Card>>

    constructor(cards: List<Card>, player: Player) {
        this.cards = cards.sortedBy { card -> card.value }
        this.player = player
        result = this.calculateResult(this.cards)
    }

    fun calculateResult(cards: List<Card>): Pair<HandResult, List<Card>> {
        when {
            HandResult.isStraight(cards) -> return calculateStraightType(cards)
            HandResult.isPoker(cards) -> return Pair(HandResult.POKER, getCombination(HandResult.POKER))
            HandResult.isFull(cards) -> return Pair(HandResult.FULL, getCombination(HandResult.FULL))
            HandResult.areSameType(cards) -> return Pair(HandResult.FLUSH, getCombination(HandResult.FLUSH))
            HandResult.isThreeOfAKind(cards) -> return Pair(HandResult.THREE_OF_A_KIND, getCombination(HandResult.THREE_OF_A_KIND))
            HandResult.isTwoPairs(cards) -> return Pair(HandResult.TWO_PAIRS, getCombination(HandResult.TWO_PAIRS))
            HandResult.isOnePair(cards) -> return Pair(HandResult.ONE_PAIR, getCombination(HandResult.ONE_PAIR))
            else -> return Pair(HandResult.HIGHEST_CARD, getCombination(HandResult.HIGHEST_CARD))
        }
    }


    fun calculateStraightType(cards: List<Card>): Pair<HandResult, List<Card>> {
        when {
            HandResult.areSameType(cards) && cards.sumBy { card -> card.value } == 60 -> return Pair(HandResult.ROYAL_FLUSH, cards)
            HandResult.areSameType(cards) && !(cards.sumBy { card -> card.value } == 60) -> return Pair(HandResult.STRAIGHT_FLUSH, cards)
            else -> return Pair(HandResult.STRAIGHT, cards)
        }
    }

    fun getCombination(handResult: HandResult): List<Card> {
        when (handResult) {
            HandResult.POKER -> return getUniqueHandCombination(4)
            HandResult.THREE_OF_A_KIND -> return getUniqueHandCombination(3)
            HandResult.TWO_PAIRS -> return getMultipleHandCombination(2)
            HandResult.ONE_PAIR -> return getUniqueHandCombination(2)
            HandResult.HIGHEST_CARD -> return listOf<Card>(cards.maxBy { card -> card.value }!!)
            else -> return cards
        }
    }

    fun getUncombinedCards(): List<Card> {
        return cards.minus(getCombination(result.first))
    }

    fun getMaxUncombinedCard():Card{
        return getUncombinedCards().maxBy { card -> card.value }!!
    }

    fun getUniqueHandCombination(quantity:Int):List<Card>{
        return cards.groupBy { card -> card.value }.entries.filter { set -> set.value.size == quantity }.first().value
    }

    fun getMultipleHandCombination(quantityOfGroups:Int):List<Card>{
        return cards.groupBy { card -> card.value }.entries.filter { set -> set.value.size == quantityOfGroups }.flatMap { set -> set.value }
    }
}