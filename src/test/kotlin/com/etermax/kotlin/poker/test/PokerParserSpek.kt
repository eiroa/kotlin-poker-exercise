package com.etermax.kotlin.poker.test

import com.etermax.kotlin.poker.domain.*
import org.amshove.kluent.shouldEqual
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

/**
 * Created by eiroa on 8/8/17.
 */
class PokerParserSpek : Spek({
    describe("Validate individual cards") {
        given(" a diamonds 2 card string representation") {
            val cardString = "2D"
            on("parsing string card") {
                val card = Card(cardString)
                it("should return a 2 diamond card") {
                    card.type shouldEqual CardType.DIAMONDS
                    card.value shouldEqual 2
                }
            }
        }

        given(" a spides king string representation") {
            val cardString = "KS"
            on("parsing string card") {
                val card = Card(cardString)
                it("should return a spides king card") {
                    card.type shouldEqual CardType.SPIDES
                    card.value shouldEqual 13
                }
            }
        }

        given(" a clovers Ace string representation") {
            val cardString = "AC"
            on("parsing string card") {
                val card = Card(cardString)
                it("should return a clovers ace card") {
                    card.type shouldEqual CardType.CLOVERS
                    card.value shouldEqual 14
                }
            }
        }

        given(" a hearts Jack string representation") {
            val cardString = "JH"
            on("parsing string card") {
                val card = Card(cardString)
                it("should return a hearts Jack card") {
                    card.type shouldEqual CardType.HEARTS
                    card.value shouldEqual 11
                }
            }
        }

        given(" a hearts 10 string representation") {
            val cardString = "TH"
            on("parsing string card") {
                val card = Card(cardString)
                it("should return a hearts 10 card") {
                    card.type shouldEqual CardType.HEARTS
                    card.value shouldEqual 10
                }
            }
        }
    }

    describe("Validate hand representation") {
        given(" a text with 1000 hands for 2 players") {
            val textFile = "poker.txt"
            on("parsing file") {
                val handsList = Thread.currentThread().contextClassLoader.getResourceAsStream("poker.txt").reader().readLines()
                it("should  validate 1000 hands") {
                    handsList.size shouldEqual 1000
                }
                it("should validate 10 cards for the round 3") {
                    var cardsToParse = handsList.get(2).replace("\\s".toRegex(), "")

                    val cards = CardParser.stringToListOfCards(cardsToParse)
                    cards.size shouldEqual 10
                }
            }
        }

        given(" a text with 2 hand representation where players1 is a royal flush but not players2") {
            val cardsText = "THJHQHKHAH7SKSKC6H2C"
            on("parsing cards") {
                val cards = CardParser.stringToListOfCards(cardsText)
                val p1Hand = Hand(cards.take(5),Player.P1)
                val p2Hand = Hand(cards.takeLast(5),Player.P2)
                val round = Round(p1Hand,p2Hand)
                it("should  validate that Hand for p1 are all of the same type") {
                    HandResult.areSameType(round.player1Hand.cards) shouldEqual true
                }
                it("should  validate that Hand for p1 is straight") {
                    HandResult.isStraight(round.player1Hand.cards) shouldEqual true
                }

                it("should  validate that Hand for p1 is royal flush") {
                    HandResult.isRoyalFlush(round.player1Hand.cards) shouldEqual true
                }

                it("should  validate that Hand for p2 are not all of the same type") {
                    HandResult.areSameType(round.player2Hand.cards) shouldEqual false
                }

                it("should  validate that Hand for p2 is not straight") {
                    HandResult.isStraight(round.player2Hand.cards) shouldEqual  false
                }
                it("should  validate that Hand for p1 isnot  royal flush") {
                    HandResult.isRoyalFlush(round.player2Hand.cards) shouldEqual false
                }
            }
        }

        given(" a text with 2 hand representation where players1 is poker") {
            val cardsText = "3H3C3S3DAH"
            on("parsing cards") {
                val cards = CardParser.stringToListOfCards(cardsText)
                val p1Hand = Hand(cards,Player.P1)
                it("should  validate that Hand for p1 is poker") {
                    HandResult.isPoker(p1Hand.cards) shouldEqual true
                }
            }
        }

        given(" a text with 2 hand representation where players1 is full") {
            val cardsText = "3H3C3S4D4H"
            on("parsing cards") {
                val cards = CardParser.stringToListOfCards(cardsText)
                val p1Hand = Hand(cards,Player.P1)
                it("should  validate that Hand for p1 is poker") {
                    HandResult.isFull(p1Hand.cards) shouldEqual true
                }
            }
        }
    }


})