package com.etermax.kotlin.poker.domain


class Hand {
    val cards:List<Card>
    val player:Player
    var result:HandResult = HandResult.HIGHEST_CARD

    constructor( cards:List<Card>,  player:Player){
        this.cards = cards
        this.player= player
    }
}