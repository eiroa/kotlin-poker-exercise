package com.etermax.kotlin.poker.core

import com.etermax.kotlin.poker.core.HandRank.*
import java.util.stream.Collectors.counting
import java.util.stream.Collectors.groupingBy

class RankedHand(hand: Hand) {

    var rank: HandRank

    init {
        val cardsRankCountMap = hand.cards.stream().collect(groupingBy(Card::rank, counting()))

        if (isStraight(hand) && isFlush(hand) && hand.cards.sortedBy { it.rank.points }.last().rank == CardRank.ACE) {
            rank = ROYAL_FLUSH
        } else if (isStraight(hand) && isFlush(hand)) {
            rank = STRAIGHT_FLUSH
        } else if (cardsRankCountMap.containsValue(4)) {
            rank = FOUR_OF_A_KIND
        } else if (cardsRankCountMap.containsValue(3) && cardsRankCountMap.containsValue(2)) {
            rank = FULL_HOUSE
        } else if (isFlush(hand)) {
            rank = FLUSH
        } else if (isStraight(hand)) {
            rank = STRAIGHT
        } else if (cardsRankCountMap.containsValue(3)) {
            rank = THREE_OF_A_KIND
        } else if (cardsRankCountMap.values.count { 2L == it } == 2) {
            rank = TWO_PAIR
        } else if (cardsRankCountMap.containsValue(2)) {
            rank = ONE_PAIR
        } else {
            rank = HIGH_CARD
        }
    }

    fun isGraterThan(rankedHand: RankedHand): Boolean {
        //TODO: implementar
        return true
    }

    private fun isStraight(hand: Hand): Boolean {
        val cardSortedByPoints = hand.cards.sortedBy { it.rank.points }.toMutableList()
        if (cardSortedByPoints.last().rank == CardRank.ACE && cardSortedByPoints.first().rank == CardRank.TWO) {
            cardSortedByPoints.removeAt(cardSortedByPoints.size - 1)
        }
        return (0..cardSortedByPoints.size - 2).none { (cardSortedByPoints[it].rank.points + 1) != cardSortedByPoints[it + 1].rank.points }
    }

    private fun isFlush(hand: Hand): Boolean {
        return (0..hand.cards.size - 2).all { hand.cards[it].suit == hand.cards[it + 1].suit }
    }
}