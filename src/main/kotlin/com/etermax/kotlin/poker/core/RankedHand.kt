package com.etermax.kotlin.poker.core

import com.etermax.kotlin.poker.core.HandRank.*
import java.util.stream.Collectors.counting
import java.util.stream.Collectors.groupingBy

class RankedHand(hand: Hand) {

    var rank: HandRank

    val value: CardRank

    init {
        val cardsRankCountMap = hand.cards.stream().collect(groupingBy(Card::rank, counting()))

        if (isStraight(hand) && isFlush(hand) && hand.cards.sortedBy { it.rank.points }.last().rank == CardRank.ACE) {
            rank = ROYAL_FLUSH
            value = cardsRankCountMap.keys.maxBy { it.points }!!
        } else if (isStraight(hand) && isFlush(hand)) {
            rank = STRAIGHT_FLUSH
            value = cardsSortedAsStraight(hand).last().rank
        } else if (cardsRankCountMap.containsValue(4)) {
            rank = FOUR_OF_A_KIND
            value = cardsRankCountMap.filter { it.value == 4L }.keys.maxBy { it.points }!!
        } else if (cardsRankCountMap.containsValue(3) && cardsRankCountMap.containsValue(2)) {
            rank = FULL_HOUSE
            value = cardsRankCountMap.keys.maxBy { it.points }!!
        } else if (isFlush(hand)) {
            rank = FLUSH
            value = cardsRankCountMap.keys.maxBy { it.points }!!
        } else if (isStraight(hand)) {
            rank = STRAIGHT
            value = cardsSortedAsStraight(hand).last().rank
        } else if (cardsRankCountMap.containsValue(3)) {
            rank = THREE_OF_A_KIND
            value = cardsRankCountMap.filter { it.value == 3L }.keys.maxBy { it.points }!!
        } else if (cardsRankCountMap.values.count { 2L == it } == 2) {
            rank = TWO_PAIR
            value = cardsRankCountMap.filter { it.value == 2L }.keys.maxBy { it.points }!!
        } else if (cardsRankCountMap.containsValue(2)) {
            rank = ONE_PAIR
            value = cardsRankCountMap.filter { it.value == 2L }.keys.maxBy { it.points }!!
        } else {
            rank = HIGH_CARD
            value = cardsRankCountMap.keys.maxBy { it.points }!!
        }
    }

    fun isGraterThan(rankedHand: RankedHand): Boolean {
        return rank.isGreaterThan(rankedHand.rank)
    }

    private fun isStraight(hand: Hand): Boolean {
        val cardSortedByPoints = cardsSortedAsStraight(hand)
        return (0..cardSortedByPoints.size - 2).none { (cardSortedByPoints[it].rank.points + 1) != cardSortedByPoints[it + 1].rank.points }
    }

    private fun cardsSortedAsStraight(hand: Hand): MutableList<Card> {
        val cardSortedByPoints = hand.cards.sortedBy { it.rank.points }.toMutableList()
        if (cardSortedByPoints.last().rank == CardRank.ACE && cardSortedByPoints.first().rank == CardRank.TWO) {
            cardSortedByPoints.removeAt(cardSortedByPoints.size - 1)
        }
        return cardSortedByPoints
    }

    private fun isFlush(hand: Hand): Boolean {
        return (0..hand.cards.size - 2).all { hand.cards[it].suit == hand.cards[it + 1].suit }
    }
}