package com.example.chess.Game_logic

interface Piece {
    val color:String?
    var coordinate: Array<Int>
    val board:Board
    val name:String

    fun isMove(cord: Array<Int>):Boolean

}