package com.example.chess.Game_logic

class Bishop(
    override val color: String?,
    override var coordinate: Array<Int>,
    override val board: Board,
    override val name: String = ""
) : Piece {
    override fun isMove(cord: Array<Int>): Boolean {
        TODO("Not yet implemented")
        return true
    }
    //private val color:String


}