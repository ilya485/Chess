package com.example.chess.Game_logic

class King(
    override val color: String?,
    override var coordinate: Array<Int>,
    override val board: Board,
    override val name: String ="King"
) : Piece {

    override fun isMove(cord: Array<Int>): Boolean {
        return true
    }


}