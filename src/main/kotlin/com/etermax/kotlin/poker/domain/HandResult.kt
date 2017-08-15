package com.etermax.kotlin.poker.domain


enum class HandResult() {
    ROYAL_FLUSH, STRAIGHT_FLUSH, POKER, FULL, FLUSH, STRAIGHT, THREE_OF_A_KIND, TWO_PAIRS, ONE_PAIR, HIGHEST_CARD;

    /**
     * All operations should operate with previously ordered cards by value representing a valid Hand
     */
    companion object Representation {
        val resultValues: HashMap<HandResult,Int> = hashMapOf( ROYAL_FLUSH to 10, STRAIGHT_FLUSH to 9, POKER to 8,FULL to 7,FLUSH to 6,
                STRAIGHT to 4, THREE_OF_A_KIND to 3,TWO_PAIRS to 2, ONE_PAIR to 1, HIGHEST_CARD to 1)


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

        fun getPoints(cards: List<Card>): Int {
            return cards.sumBy { card -> card.value }
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