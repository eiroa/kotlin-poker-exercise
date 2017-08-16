package com.etermax.kotlin.poker.domain


class Round(var player1Hand: Hand, var player2Hand: Hand) {

    fun determineWinner(): Player {
        when {
            player1Hand.result.first.value > player2Hand.result.first.value -> return player1Hand.player
            player1Hand.result.first.value < player2Hand.result.first.value -> return player2Hand.player
            else -> return compareEqualHighestCombination()
        }
    }

    fun compareEqualHighestCombination(): Player {
        when {
            player1Hand.result.second.sumBy { card -> card.value } > player2Hand.result.second.sumBy { card -> card.value } -> return player1Hand.player
            player1Hand.result.second.sumBy { card -> card.value } < player2Hand.result.second.sumBy { card -> card.value } -> return player2Hand.player
            else -> return compareHighestSpareCard()
        }
    }

    private fun compareHighestSpareCard(): Player {
        when {
            player1Hand.getUncombinedCards().maxBy { card -> card.value }!!.value > player2Hand.getUncombinedCards().maxBy { card -> card.value }!!.value -> return player1Hand.player
            player1Hand.getUncombinedCards().maxBy { card -> card.value }!!.value < player2Hand.getUncombinedCards().maxBy { card -> card.value }!!.value -> return player2Hand.player
            else -> return Player.P1 // Tie, player 1 wins by default
        }
    }
}