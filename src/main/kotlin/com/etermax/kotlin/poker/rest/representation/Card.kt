package com.etermax.kotlin.poker.rest.representation

/**
 * Created by eiroa on 8/8/17.
 */
class Card {
    val representation:String
    var type : CardType
    var value: Int

    constructor(representation:String){
        this.representation = representation
        this.value = CardParser.parseValue(representation.take(1))
        this.type = CardParser.parseType(representation.takeLast(1))
    }

}