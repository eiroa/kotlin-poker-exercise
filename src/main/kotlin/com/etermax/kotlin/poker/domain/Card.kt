package com.etermax.kotlin.poker.domain


class Card {
    val representation:String
    var type : CardType
    var value: Int

    constructor(representation:String){
        this.representation = representation
        this.value = CardParser.parseValue(representation.take(1))
        this.type = CardParser.parseType(representation.takeLast(1))
    }

    override fun toString(): String = value.toString() + type

    fun getValueAsString():String{
        when(value){
            11 -> return "Jack of " +type
            12 -> return "Queen of " + type
            13 -> return "King of " +type
            14 -> return "Ace of "+type
            else -> return value.toString() + " of " + type
        }
    }

}