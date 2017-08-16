package com.etermax.kotlin.poker.domain


enum class HandResult(val value:Int) {
    ROYAL_FLUSH(10), STRAIGHT_FLUSH(9), POKER(8), FULL(7), FLUSH(6), STRAIGHT(5), THREE_OF_A_KIND(4), TWO_PAIRS(3), ONE_PAIR(2), HIGHEST_CARD(1);

    /**
     * All operations should operate with previously ordered cards by value representing a valid Hand
     */
    companion object Representation {

        fun areSameType(cards: List<Card>): Boolean {
            return cards.all { card -> card.type.equals(cards.first().type) }
        }

        fun isStraight(cards: List<Card>): Boolean {
            return cards.mapIndexed { index, card ->
                if (index == 0) {
                    true
                } else {
                    card.value == (cards.get(index - 1).value + 1)
                }
            }.all { value -> value == true }
        }

        fun isRoyalFlush(cards: List<Card>): Boolean {
            return areSameType(cards) && isStraight(cards) && cards.sumBy { card -> card.value } == 60
        }

        fun equalCombination(cards: List<Card>, possibleValues:Int, maxValue:Int): Boolean {
            val map = cards.groupBy { card -> card.value }
            return map.keys.count() == possibleValues && map.entries.any { set ->  set.value.size == maxValue }
        }

        fun isPoker(cards: List<Card>): Boolean {
            return equalCombination(cards, 2,4)
        }

        fun isFull(cards: List<Card>): Boolean {
            return equalCombination(cards, 2,3)
        }

        fun isThreeOfAKind(cards: List<Card>): Boolean{
            return equalCombination(cards,3,3)
        }

        fun isTwoPairs(cards: List<Card>):Boolean{
            return equalCombination(cards,3,2)
        }

        fun isOnePair(cards: List<Card>):Boolean{
            return equalCombination(cards,4,2)
        }


    }

}