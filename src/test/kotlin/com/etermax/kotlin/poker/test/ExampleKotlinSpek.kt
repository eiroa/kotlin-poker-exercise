package com.etermax.kotlin.poker.test

import org.amshove.kluent.shouldEqual
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class ExampleKotlinSpek : Spek({
    describe("a example Spek") {
        given("a 'foobar' string") {
            val testData = "foobar"
            on("taking it length") {
                val dataLenght = testData.length
                it("value is 6") {
                    dataLenght shouldEqual 6
                }
            }
        }

        given("a couple or integer values") {
            val a = 2
            val b = 3
            on("executing a + b") {
                val result = a + b
                it("result is equals to 5") {
                    result shouldEqual 5
                }
            }
        }

        given("a reused varible name"){
            val a = 3
            val b = 2
            on("executing a + b"){
                val result = a + b
                it("result is again equals 5"){
                    result shouldEqual 5
                }
            }
        }
    }
})