package com.etermax.kotlin.poker.test

import com.etermax.kotlin.poker.core.HandRank
import org.amshove.kluent.shouldBe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class HandRankSpek : Spek({
    describe("a HandRank ") {
        given("a royal Flush") {
            val royalFlush = HandRank.ROYAL_FLUSH

            on("playing against all other handRanks except himself") {
                val hasWon = HandRank.values()
                        .filter { it != HandRank.ROYAL_FLUSH }
                        .fold(true, { acc, handRank -> acc && royalFlush.isGreaterThan(handRank) })
                it("it wins") {
                    hasWon shouldBe true
                }
            }
            on("playing against himself") {
                val hasWon = royalFlush.isGreaterThan(HandRank.ROYAL_FLUSH)
                it("it loses") {
                    hasWon shouldBe false
                }
            }
        }

        given("a FULL_HOUSE") {
            val fullHouse = HandRank.FULL_HOUSE

            on("playing against all other inferior handRanks") {
                val hasWon = HandRank.values()
                        .filter { it != HandRank.ROYAL_FLUSH }
                        .filter { it != HandRank.STRAIGHT_FLUSH}
                        .filter { it != HandRank.FOUR_OF_A_KIND}
                        .filter { it != HandRank.FULL_HOUSE}
                        .fold(true, { acc, handRank -> acc && fullHouse.isGreaterThan(handRank) })
                it("it wins") {
                    hasWon shouldBe true
                }
            }
            on("playing against  himself") {
                val hasWon = fullHouse.isGreaterThan(HandRank.FULL_HOUSE)
                it("it loses") {
                    hasWon shouldBe false
                }
            }

            on("playing against a superior handRank") {
                val hasWon = fullHouse.isGreaterThan(HandRank.FOUR_OF_A_KIND)
                it("it loses") {
                    hasWon shouldBe false
                }
            }
        }
    }
})