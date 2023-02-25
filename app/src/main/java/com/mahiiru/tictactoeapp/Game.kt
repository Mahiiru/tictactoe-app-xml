package com.mahiiru.tictactoeapp

class Game(private val playerX: Player = Player("X"),
           private val playerO : Player = Player("O"),
           var isGameOver : Boolean = false,
           var gameMap: Map<String,String> = mapOf("tv0x0" to "",
               "tv0x1" to "",
               "tv0x2" to "",
               "tv1x0" to "",
               "tv1x2" to "",
               "tv1x3" to "",
               "tv2x0" to "",
               "tv2x1" to "",
               "tv2x2" to "")) {

    fun gameNewPlay(play: String, isPlayerXTurn : Boolean, isPlayerOTurn: Boolean){
        if(isPlayerXTurn) {
            newPlay(play,playerX.type)
        }else if(isPlayerOTurn){
            newPlay(play,playerO.type)
        }
        if(aPlayerHasWon() || !gameMap.containsValue("")){
            this.isGameOver = true
        }
    }

    fun aPlayerHasWon() : Boolean{
        playerX.winner = calculatePlayerWinner(playerX.type)
        playerO.winner = calculatePlayerWinner(playerO.type)
        return (playerX.winner || playerO.winner)
    }

    private fun calculatePlayerWinner(type: String): Boolean = (selectColumn(type,0)
            || selectColumn(type,1)
            || selectColumn(type,2)
            || selectRow(type,0)
            || selectRow(type,1)
            || selectRow(type,2)
            || selectDiagonal(type))



    private fun selectDiagonal(type: String): Boolean = (
            (gameMap["tv0x0"].equals(type)
            && gameMap["tv1x1"].equals(type)
            && gameMap["tv2x2"].equals(type) )
            ||
            (gameMap["tv0x2"].equals(type)
            && gameMap["tv1x1"].equals(type)
            && gameMap["tv2x0"].equals(type) )
            )

    private fun selectRow(type: String,index: Int): Boolean  = (gameMap["tv${index}x0"].equals(type)
            && gameMap["tv${index}x1"].equals(type)
            && gameMap["tv${index}x2"].equals(type))

    private fun selectColumn(type: String,index: Int): Boolean = (gameMap["tv0x$index"].equals(type)
            && gameMap["tv1x$index"].equals(type)
            && gameMap["tv2x$index"].equals(type))


    fun newPlay(play : String,player: String){
        gameMap = (gameMap as MutableMap<String,String>).apply { put(play,player) }
    }

    fun isValidNewPlay(play : String) = (gameMap[play].equals(""))


}