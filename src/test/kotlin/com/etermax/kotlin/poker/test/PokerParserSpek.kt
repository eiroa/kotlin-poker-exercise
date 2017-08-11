package com.etermax.kotlin.poker.test

import com.etermax.kotlin.poker.rest.representation.Card
import com.etermax.kotlin.poker.rest.representation.CardParser
import com.etermax.kotlin.poker.rest.representation.CardType
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
    }

})