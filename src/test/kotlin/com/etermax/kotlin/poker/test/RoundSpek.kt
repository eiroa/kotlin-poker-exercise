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
        given("Two hands") {
            //Poker
            val playerAHand = Hand(listOf(Card(ACE, CLUBS), Card(ACE, DIAMONDS), Card(ACE, HEARTS), Card(ACE, SPADES), Card(ACE, CLUBS)))
            //HighHand
            val playerBHand = Hand(listOf(Card(ACE, CLUBS), Card(THREE, CLUBS), Card(FOUR, CLUBS), Card(FIVE, CLUBS), Card(SIX, CLUBS)))
            on("play a game") {
                val playerAWon = hasPlayerAWon(playerAHand, playerBHand)
                it("player one wins") {
                    playerAWon shouldBe true
                }
            }
        }
    }
})


fun hasPlayerAWon(playerAHand: Hand, playerBHand: Hand): Boolean {
    return playerAHand.beats(playerBHand)
}