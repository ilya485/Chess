package com.example.chess.Game_logic

class Knight(
    override val color: String?,
    override var coordinate: Array<Int>,
    override val board: Board,
    override val name: String = "knight"
) : Piece {
    var сanCastling=true
    override fun isMove(cord: Array<Int>): Boolean {
        return true
    }


}