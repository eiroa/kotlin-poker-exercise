package com.etermax.kotlin.poker.domain


class Hand {
    val cards: List<Card>
    val player: Player
    var result: Pair<HandResult,List<Card>>

    constructor(cards: List<Card>, player: Player) {
        this.cards = cards.sortedBy {  card -> card.value }
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
            HandResult.areSameType(cards) && cards.sumBy { card -> card.value } == 60 -> return Pair(HandResult.ROYAL_FLUSH, getCombination(HandResult.ROYAL_FLUSH))
            HandResult.areSameType(cards) && !(cards.sumBy { card -> card.value } == 60) -> return Pair(HandResult.STRAIGHT_FLUSH, getCombination(HandResult.STRAIGHT_FLUSH))
            else -> return Pair(HandResult.STRAIGHT, getCombination(HandResult.STRAIGHT))
        }
    }

    fun getCombination(handResult: HandResult): List<Card> {
        when (handResult) {
            HandResult.POKER -> return cards.groupBy { card -> card.value }.entries.filter { set -> set.value.size == 4 }.first().value
            HandResult.THREE_OF_A_KIND -> return cards.groupBy { card -> card.value }.entries.filter { set -> set.value.size == 3 }.first().value
            HandResult.TWO_PAIRS -> return cards.groupBy { card -> card.value }.entries.filter { set -> set.value.size == 2 }.flatMap { set -> set.value }
            HandResult.ONE_PAIR -> return cards.groupBy { card -> card.value }.entries.filter { set -> set.value.size == 2 }.first().value
            HandResult.HIGHEST_CARD -> return listOf<Card>(cards.maxBy { card -> card.value }!!)
            else -> return cards
        }
    }

    fun getUncombinedCards(handResult: HandResult):List<Card>{
        when(handResult){
            HandResult.POKER -> return cards.groupBy { card -> card.value }.entries.filter { set -> set.value.size == 1 }.first().value
            HandResult.THREE_OF_A_KIND -> return cards.groupBy { card -> card.value }.entries.filter { set -> set.value.size == 1 }.flatMap{ set -> set.value }
            HandResult.TWO_PAIRS -> return cards.groupBy { card -> card.value }.entries.filter { set -> set.value.size == 1 }.first().value
            HandResult.ONE_PAIR -> return cards.groupBy { card -> card.value }.entries.filter { set -> set.value.size == 1 }.flatMap{ set -> set.value }
            HandResult.HIGHEST_CARD -> return cards.filter { card -> card.value != result.second.first().value }
            else -> return  emptyList<Card>()
        }
    }
}