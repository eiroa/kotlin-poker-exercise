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
            player1Hand.getMaxUncombinedCard().value > player2Hand.getMaxUncombinedCard().value -> return player1Hand.player
            player1Hand.getMaxUncombinedCard().value < player2Hand.getMaxUncombinedCard().value -> return player2Hand.player
            else -> return Player.P1 // Tie, player 1 wins by default
        }
    }

    fun getHandForPlayer(player:Player):Hand{
        when(player){
            Player.P1 -> return player1Hand
            Player.P2 -> return player2Hand
            else -> return player1Hand
        }
    }

}