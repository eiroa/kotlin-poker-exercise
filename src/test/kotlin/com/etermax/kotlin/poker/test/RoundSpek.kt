package com.etermax.kotlin.poker.test

import com.etermax.kotlin.poker.core.Card
import com.etermax.kotlin.poker.core.CardRank.*
import com.etermax.kotlin.poker.core.Hand
import com.etermax.kotlin.poker.core.Suit.*
import org.amshove.kluent.shouldBe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on


/**
 * Created by Matias on 7/25/2017.
 */
class RoundSpek : Spek({
    describe("a Game") {
        given("poker vs higHand") {
            //Poker
            val playerAHand = Hand(listOf(Card(ACE, CLUBS), Card(ACE, DIAMONDS), Card(ACE, HEARTS), Card(ACE, SPADES), Card(QUEEN, CLUBS)))
            //HighHand
            val playerBHand = Hand(listOf(Card(ACE, CLUBS), Card(THREE, CLUBS), Card(FOUR, CLUBS), Card(FIVE, CLUBS), Card(SIX, CLUBS)))
            on("play a game") {
                val playerAWon = hasPlayerAWon(playerAHand, playerBHand)
                it("player one wins") {
                    playerAWon shouldBe true
                }
            }
        }

        given("full house vs poker") {
            //full house
            val playerAHand = Hand(listOf(Card(ACE, CLUBS), Card(ACE, DIAMONDS), Card(ACE, HEARTS), Card(QUEEN, SPADES), Card(QUEEN, CLUBS)))
            //Poker
            val playerBHand = Hand(listOf(Card(ACE, CLUBS), Card(ACE, DIAMONDS), Card(ACE, HEARTS), Card(ACE, SPADES), Card(QUEEN, CLUBS)))
            on("play a game") {
                val playerAWon = hasPlayerAWon(playerAHand, playerBHand)
                it("player one lose") {
                    playerAWon shouldBe false
                }
            }
        }
    }
})


fun hasPlayerAWon(playerAHand: Hand, playerBHand: Hand): Boolean {
    return playerAHand.rankedHand.isGraterThan(playerBHand.rankedHand)
}