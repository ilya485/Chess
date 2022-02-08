package com.example.chess.Game_logic

open class Board() {
    public var boardPiece = arrayOfNulls<Array<Piece?>>(10)
    var isCheck = false
    var isCheckmate = false
    var isStalemate = false
    var kingBlackPos = arrayOf(0, 4)
    var kingWhitePos = arrayOf(7, 4)
    var passant: Pawn? = null
    var specialMove = arrayOf(arrayOf(-1, -1), arrayOf(-1, -1))

    /*створюємо змінну в якій будуть координати початкові та кінцеві спец. ходів
    якщо кінцеві -1-1 то видаляємо фігуру*/

    //TODO оновлення дошки в UI
    init {

        boardPiece[7] = arrayOf(
            Rock("black", arrayOf(7, 0), this), //TODO final this
            Knight("black", arrayOf(7, 1), this),
            Bishop("black", arrayOf(7, 2), this),
            Queen("black", arrayOf(7, 3), this),
            King("black", arrayOf(7, 4), this),
            Bishop("black", arrayOf(7, 5), this),
            Knight("black", arrayOf(7, 6), this),
            Rock("black", arrayOf(7, 7), this)
        )
        boardPiece[6] = Array(8, { i -> Pawn("black", arrayOf(6, i), this) })


        boardPiece[0] = arrayOf(
            Rock("white", arrayOf(0, 0), this),
            Knight("white", arrayOf(0, 1), this),
            Bishop("white", arrayOf(0, 2), this),
            Queen("white", arrayOf(0, 3), this),
            King("white", arrayOf(0, 4), this),
            Bishop("white", arrayOf(0, 5), this),
            Knight("white", arrayOf(0, 6), this),
            Rock("white", arrayOf(0, 7), this)
        )
        boardPiece[1] = Array(8, { i -> Pawn("white", arrayOf(1, i), this) })

        for (i in 2..5) {
            boardPiece[i] = arrayOfNulls(8)
        }
    }


    fun moveRule(coord: Array<Array<Int>>): Boolean {
        //return true
        //if (boardPiece[coord[0][0]]?.get(coord[0][1]) ==null){return false}
        //boardPiece[a]?.get(b)
        val piece: Piece? = boardPiece[coord[0][0]]?.get(coord[0][1])
        val move = piece?.isMove(coord[1])
        if (move == true) {
            boardPiece[coord[1][0]]?.set(coord[1][1], boardPiece[coord[0][0]]?.get(coord[0][1]))
            boardPiece[coord[0][0]]?.set(coord[0][1], null)
            return true
        }
        return false
    }

    fun moveWithoutRule(coord: Array<Array<Int>>) {

    }

}