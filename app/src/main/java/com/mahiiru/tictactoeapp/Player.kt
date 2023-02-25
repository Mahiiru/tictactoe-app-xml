package com.mahiiru.tictactoeapp

class Player(val type: String,
             var winner: Boolean = false) {

    fun playerHasWon() = "Player $type has won"

}