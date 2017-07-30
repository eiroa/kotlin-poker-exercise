package com.etermax.kotlin.poker.core.rankedhand

import com.etermax.kotlin.poker.core.Card
import com.etermax.kotlin.poker.core.CardRank
import com.etermax.kotlin.poker.core.Hand
import java.util.stream.Collectors

open class RankedHandFactory {

    fun create(hand: Hand): RankedHand {
        if (isStraight(hand) && isFlush(hand) && hand.cards.sortedBy { it.rank.points }.last().rank == CardRank.ACE) {
            return RoyalFlush(hand)
        } else if (isStraight(hand) && isFlush(hand)) {
            return straightFlush(hand)
        } else if (cardsRankCountMap(hand).containsValue(4)) {
            return fourOfAKind(hand)
        } else if (cardsRankCountMap(hand).containsValue(3) && cardsRankCountMap(hand).containsValue(2)) {
            return fullHouse(hand)
        } else if (isFlush(hand)) {
            return flush(hand)
        } else if (isStraight(hand)) {
            return straight(hand)
        } else if (cardsRankCountMap(hand).containsValue(3)) {
            return threeOfAKind(hand)
        } else if (cardsRankCountMap(hand).values.count { 2L == it } == 2) {
            return twoPair(hand)
        } else if (cardsRankCountMap(hand).containsValue(2)) {
            return onePair(hand)
        } else {
            return highCard(hand)
        }
    }

    private fun RoyalFlush(hand: Hand): RankedHand {
        return RankedHand(HandRank.ROYAL_FLUSH, {  cardsSortedAsStraight(hand).last().rank })
    }

    private fun straightFlush(hand: Hand): RankedHand {
        return RankedHand(HandRank.STRAIGHT_FLUSH, {  cardsSortedAsStraight(hand).last().rank })
    }

    private fun fourOfAKind(hand: Hand): RankedHand {
        return RankedHand(HandRank.FOUR_OF_A_KIND, {  getRepeatedCardRanksIn(hand = hand, times = 4).maxBy { it.points }!! })
    }

    private fun fullHouse(hand: Hand): RankedHand {
        return RankedHand(HandRank.FULL_HOUSE, {  getRepeatedCardRanksIn(hand = hand).maxBy { it.points }!! })
    }

    private fun flush(hand: Hand): RankedHand {
        return RankedHand(HandRank.FLUSH, {  getRepeatedCardRanksIn(hand = hand).maxBy { it.points }!! })
    }

    private fun straight(hand: Hand): RankedHand {
        return RankedHand(HandRank.STRAIGHT, {  cardsSortedAsStraight(hand).last().rank })
    }

    private fun threeOfAKind(hand: Hand): RankedHand {
        return RankedHand(HandRank.THREE_OF_A_KIND, { getRepeatedCardRanksIn(hand = hand, times = 3).maxBy { it.points }!!})
    }

    private fun twoPair(hand: Hand): RankedHand {
        return RankedHand(HandRank.TWO_PAIR, { getRepeatedCardRanksIn(hand = hand, times = 2).maxBy { it.points }!!})
    }

    private fun onePair(hand: Hand): RankedHand {
        return RankedHand(HandRank.ONE_PAIR, { getRepeatedCardRanksIn(hand = hand, times = 2).maxBy { it.points }!!})
    }

    private fun highCard(hand: Hand): RankedHand {
        return RankedHand(HandRank.HIGH_CARD, { getRepeatedCardRanksIn(hand = hand).maxBy { it.points }!!})
    }

    private fun getRepeatedCardRanksIn(hand: Hand, times: Long = 1): Set<CardRank> {
        return cardsRankCountMap(hand).filter { it.value == times }.keys
    }

    private fun getRepeatedCardRanksIn(hand: Hand): Set<CardRank> {
        return cardsRankCountMap(hand).keys
    }

    private fun cardsRankCountMap(hand: Hand): MutableMap<CardRank, Long> {
        return hand.cards.stream()
                .collect(Collectors.groupingBy(Card::rank, Collectors.counting()))
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