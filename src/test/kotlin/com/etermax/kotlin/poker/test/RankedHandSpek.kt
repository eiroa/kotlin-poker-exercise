package com.etermax.kotlin.poker.test

import com.etermax.kotlin.poker.core.*
import com.etermax.kotlin.poker.core.CardRank.*
import com.etermax.kotlin.poker.core.Suit.*
import org.amshove.kluent.shouldBe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on


class RankedHandSpek : Spek ({
    describe("a high card ranked hand") {
        given ("a hand with highest card rank"){
            val hand = Hand(listOf(Card(THREE, DIAMONDS),Card(TWO, CLUBS),Card(SIX, DIAMONDS),Card(KING, DIAMONDS),
                    Card(SEVEN, HEARTS)))
            on ("building a rankedHand"){
                val rankedHand = RankedHand(hand)
                it ("ranked hand is HIGH CARD"){
                    rankedHand.rank shouldBe HandRank.HIGH_CARD
                }
            }
        }
    }

    describe("a pair ranked hand") {
        given ("a hand with one pair rank"){
            val hand = Hand(listOf(Card(TWO, DIAMONDS),Card(TWO, CLUBS),Card(SIX, DIAMONDS),Card(KING, DIAMONDS),Card(THREE, HEARTS)))
            on ("building a rankedHand"){
                val rankedHand = RankedHand(hand)
                it ("ranked hand is ONE PAIR"){
                    rankedHand.rank shouldBe HandRank.ONE_PAIR
                }
            }
        }
    }

    describe("a two pair ranked hand") {
        given ("a hand with two pair rank"){
            val hand = Hand(listOf(Card(TWO, DIAMONDS),Card(TWO, CLUBS),Card(SIX, DIAMONDS),Card(KING, DIAMONDS),Card(SIX, HEARTS)))
            on ("building a rankedHand"){
                val rankedHand = RankedHand(hand)
                it ("ranked hand is TWO PAIR"){
                    rankedHand.rank shouldBe HandRank.TWO_PAIR
                }
            }
        }
    }

    describe("a three of a kind ranked hand") {
        given ("a hand with three of a kind rank"){
            val hand = Hand(listOf(Card(TWO, DIAMONDS),Card(TWO, CLUBS),Card(SIX, DIAMONDS),Card(KING, DIAMONDS),Card(TWO, HEARTS)))
            on ("building a rankedHand"){
                val rankedHand = RankedHand(hand)
                it ("ranked hand is THREE OF A KIND"){
                    rankedHand.rank shouldBe HandRank.THREE_OF_A_KIND
                }
            }
        }
    }

    describe("a straight ranked hand") {
        given ("a hand with a straight rank"){
            val hand = Hand(listOf(Card(FIVE, DIAMONDS),Card(SEVEN, CLUBS),Card(SIX, DIAMONDS),Card(NINE, DIAMONDS),Card(EIGHT, HEARTS)))
            on ("building a rankedHand"){
                val rankedHand = RankedHand(hand)
                it ("ranked hand is STRAIGHT"){
                    rankedHand.rank shouldBe HandRank.STRAIGHT
                }
            }
        }
    }

    describe("a straight ending with ACE ranked hand") {
        given ("a hand with a straight rank"){
            val hand = Hand(listOf(Card(TEN, DIAMONDS),Card(KING, CLUBS),Card(ACE, DIAMONDS),Card(JACK, DIAMONDS),Card(QUEEN, HEARTS)))
            on ("building a rankedHand"){
                val rankedHand = RankedHand(hand)
                it ("ranked hand is STRAIGHT"){
                    rankedHand.rank shouldBe HandRank.STRAIGHT
                }
            }
        }
    }

    describe("a straight starting with ACE ranked hand") {
        given ("a hand with a straight rank"){
            val hand = Hand(listOf(Card(THREE, DIAMONDS),Card(TWO, CLUBS),Card(ACE, DIAMONDS),Card(FOUR, DIAMONDS),Card(FIVE, HEARTS)))
            on ("building a rankedHand"){
                val rankedHand = RankedHand(hand)
                it ("ranked hand is STRAIGHT"){
                    rankedHand.rank shouldBe HandRank.STRAIGHT
                }
            }
        }
    }

    describe("a flush ranked hand") {
        given ("a hand with a flush rank"){
            val hand = Hand(listOf(Card(FIVE, DIAMONDS),Card(FIVE, DIAMONDS),Card(SIX, DIAMONDS),Card(NINE, DIAMONDS),Card(EIGHT, DIAMONDS)))
            on ("building a rankedHand"){
                val rankedHand = RankedHand(hand)
                it ("ranked hand is FLUSH"){
                    rankedHand.rank shouldBe HandRank.FLUSH
                }
            }
        }
    }

    describe("a full house ranked hand") {
        given ("a hand with full house rank"){
            val hand = Hand(listOf(Card(TWO, DIAMONDS),Card(TWO, CLUBS),Card(KING, DIAMONDS),Card(KING, DIAMONDS),Card(TWO, HEARTS)))
            on ("building a rankedHand"){
                val rankedHand = RankedHand(hand)
                it ("ranked hand is FULL HOUSE"){
                    rankedHand.rank shouldBe HandRank.FULL_HOUSE
                }
            }
        }
    }

    describe("a four of a kind ranked hand") {
        given ("a hand with four of a kind rank"){
            val hand = Hand(listOf(Card(TWO, DIAMONDS),Card(TWO, CLUBS),Card(KING, DIAMONDS),Card(TWO, SPADES),Card(TWO, HEARTS)))
            on ("building a rankedHand"){
                val rankedHand = RankedHand(hand)
                it ("ranked hand is FOUR OF A KIND"){
                    rankedHand.rank shouldBe HandRank.FOUR_OF_A_KIND
                }
            }
        }
    }

    describe("a straight flush ranked hand") {
        given ("a hand with a straight flush rank"){
            val hand = Hand(listOf(Card(FIVE, DIAMONDS),Card(SEVEN, DIAMONDS),Card(SIX, DIAMONDS),Card(NINE, DIAMONDS),Card(EIGHT, DIAMONDS)))
            on ("building a rankedHand"){
                val rankedHand = RankedHand(hand)
                it ("ranked hand is STRAIGHT FLUSH"){
                    rankedHand.rank shouldBe HandRank.STRAIGHT_FLUSH
                }
            }
        }
    }

    describe("a royal ranked hand") {
        given ("a hand with a royal flush rank"){
            val hand = Hand(listOf(Card(JACK, DIAMONDS),Card(TEN, DIAMONDS),Card(ACE, DIAMONDS),Card(KING, DIAMONDS),Card(QUEEN, DIAMONDS)))
            on ("building a rankedHand"){
                val rankedHand = RankedHand(hand)
                it ("ranked hand is ROYAL FLUSH"){
                    rankedHand.rank shouldBe HandRank.ROYAL_FLUSH
                }
            }
        }
    }

})