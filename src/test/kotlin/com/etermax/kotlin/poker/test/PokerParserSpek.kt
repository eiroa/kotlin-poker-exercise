package com.etermax.kotlin.poker.test

import com.etermax.kotlin.poker.domain.*
import com.etermax.kotlin.poker.domain.GameReferee
import org.amshove.kluent.shouldEqual
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on


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

        given(" a text with 2 hand representation where players1 is a royal flush but not players2") {
            val cardsText = "JHTHAHKHQH7SKSKC6H2C"
            on("parsing cards") {
                val cards = CardParser.stringToListOfCards(cardsText)
                val p1Hand = Hand(cards.take(5),Player.P1)
                val p2Hand = Hand(cards.takeLast(5),Player.P2)
                val round = Round(p1Hand,p2Hand)

                it("should  validate that Hand for p1 is royal flush") {
                    p1Hand.result.first shouldEqual HandResult.ROYAL_FLUSH
                    p1Hand.result.second.size shouldEqual 5
                }

                it("should  validate that Hand for p1 is not  royal flush") {
                    HandResult.isRoyalFlush(p2Hand.cards) shouldEqual false
                }
            }
        }

        given(" a text with a hand representation which is straight flush") {
            val cardsText = "3H4H5H6H7H"
            on("parsing cards") {
                val cards = CardParser.stringToListOfCards(cardsText)
                val p1Hand = Hand(cards,Player.P1)
                it("should  validate that Hand for p1 is poker") {
                    p1Hand.result.first  shouldEqual HandResult.STRAIGHT_FLUSH
                    p1Hand.result.second.size shouldEqual 5
                }
            }
        }

        given(" a text with a hand representation which is poker") {
            val cardsText = "3H3C3S3DAH"
            on("parsing cards") {
                val cards = CardParser.stringToListOfCards(cardsText)
                val p1Hand = Hand(cards,Player.P1)
                it("should  validate that Hand for p1 is poker") {
                    p1Hand.result.first  shouldEqual HandResult.POKER
                    p1Hand.result.second.size shouldEqual 4
                }
            }
        }

        given(" a text with a hand representation which is  full") {
            val cardsText = "3H3C3S4D4H"
            on("parsing cards") {
                val cards = CardParser.stringToListOfCards(cardsText)
                val p1Hand = Hand(cards,Player.P1)
                it("should  validate that Hand for p1 is full") {
                    p1Hand.result.first  shouldEqual HandResult.FULL
                    p1Hand.result.second.size shouldEqual 5
                }
            }
        }

        given(" a text with a hand representation which is flush") {
            val cardsText = "3H4HAH8HJH"
            on("parsing cards") {
                val cards = CardParser.stringToListOfCards(cardsText)
                val p1Hand = Hand(cards,Player.P1)
                it("should  validate that Hand for p1 is full") {
                    p1Hand.result.first  shouldEqual HandResult.FLUSH
                    p1Hand.result.second.size shouldEqual 5
                }
            }
        }

        given(" a text with a hand representation which is straight") {
            val cardsText = "3H4D5H6D7S"
            on("parsing cards") {
                val cards = CardParser.stringToListOfCards(cardsText)
                val p1Hand = Hand(cards,Player.P1)
                it("should  validate that Hand for p1 is full") {
                    p1Hand.result.first  shouldEqual HandResult.STRAIGHT
                    p1Hand.result.second.size shouldEqual 5
                }
            }
        }

        given(" a text with a hand representation which is  three of a kind") {
            val cardsText = "3H3C3S4D2H"
            on("parsing cards") {
                val cards = CardParser.stringToListOfCards(cardsText)
                val p1Hand = Hand(cards,Player.P1)
                it("should  validate that Hand for p1 is three of a kind") {
                    p1Hand.result.first  shouldEqual HandResult.THREE_OF_A_KIND
                    p1Hand.result.second.size shouldEqual 3
                }
            }
        }

        given(" a text with a hand representation which is  two pairs") {
            val cardsText = "3H3C8S4D4H"
            on("parsing cards") {
                val cards = CardParser.stringToListOfCards(cardsText)
                val p1Hand = Hand(cards,Player.P1)
                it("should  validate that Hand for p1 is two pairs") {
                    p1Hand.result.first  shouldEqual HandResult.TWO_PAIRS
                    p1Hand.result.second.size shouldEqual 4
                }
            }
        }

        given(" a text with a hand representation which is  one pair") {
            val cardsText = "3H3C8S2DAH"
            on("parsing cards") {
                val cards = CardParser.stringToListOfCards(cardsText)
                val p1Hand = Hand(cards,Player.P1)
                it("should  validate that Hand for p1 is one pair") {
                    p1Hand.result.first shouldEqual HandResult.ONE_PAIR
                    p1Hand.result.second.size shouldEqual 2
                }
            }
        }

        given(" a text with a hand representation which is just HighestCard") {
            val cardsText = "3H6C8S9DKH"
            on("parsing cards") {
                val cards = CardParser.stringToListOfCards(cardsText)
                val p1Hand = Hand(cards,Player.P1)
                it("should  validate that Hand for p1 is just highest card") {
                    p1Hand.result.first shouldEqual HandResult.HIGHEST_CARD
                    p1Hand.result.second.size shouldEqual 1
                }
            }
        }
    }





    describe("Validate Round results") {

        given(" a text with 2 hand representation where players1 is a royal flush but not players2") {
            val cardsText = "THJHQHKHAH7SKSKC6H2C"
            on("parsing cards") {
                val cards = CardParser.stringToListOfCards(cardsText)
                val p1Hand = Hand(cards.take(5),Player.P1)
                val p2Hand = Hand(cards.takeLast(5),Player.P2)
                val round = Round(p1Hand,p2Hand)

                it("should  validate that p1 is winner") {
                    round.determineWinner() shouldEqual Player.P1
                }

            }
        }

        given(" a text with 2 hand representation where both hands are poker, yet p2 hand is a better poker hand") {
            val cardsText = "8H8H8HKH8HJSJHJD6HJC"
            on("parsing cards") {
                val cards = CardParser.stringToListOfCards(cardsText)
                val p1Hand = Hand(cards.take(5),Player.P1)
                val p2Hand = Hand(cards.takeLast(5),Player.P2)
                val round = Round(p1Hand,p2Hand)

                it("should  validate that p2 is winner") {
                    round.determineWinner() shouldEqual Player.P2
                }

            }
        }

        given(" a text with 2 hand representation where both hands are Three of a kind, yet p1 hand has better uncombined highest card ") {
            val cardsText = "8H8H8HKC2DJS8C7D8C8C"
            on("parsing cards") {
                val cards = CardParser.stringToListOfCards(cardsText)
                val p1Hand = Hand(cards.take(5),Player.P1)
                val p2Hand = Hand(cards.takeLast(5),Player.P2)
                val round = Round(p1Hand,p2Hand)

                it("should  validate that p1 is winner") {
                    round.determineWinner() shouldEqual Player.P1
                }

            }
        }
    }

    describe(" validate a full game"){
        given(" a text with 1000 rounds for 2 players") {
            val textFile = "poker.txt"
            on("parsing file") {
                val game = GameReferee(textFile)
                it("should  validate 1000 Rounds") {
                    game.rounds.size shouldEqual 1000
                }
                it("should validate 10 cards for each round") {
                    game.rounds.all{ round -> round.player1Hand.cards.size == 5  && round.player2Hand.cards.size == 5} shouldEqual true
                }

                it("should validate that victories for p1 and p2 equals 1000") {
                    game.getVictoriesForPlayer(Player.P1) + game.getVictoriesForPlayer(Player.P2) shouldEqual 1000
                }

                it("should validate that winner is P2") {
                    game.finalWinner shouldEqual Player.P2
                }
            }
        }
    }


})