package com.example.chess.Game_logic

class Rock(
    override val color: String?,
    override var coordinate: Array<Int>,
    override val board: Board,
    override val name: String = "rock"

) : Piece {

    override fun isMove(cord: Array<Int>): Boolean {
        if (cord[0]==coordinate[0] || cord[1]==coordinate[1]){

            return true
        }
        return false
    }


}