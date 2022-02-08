package com.example.chess.Game_logic

import kotlin.math.absoluteValue

class Pawn(
    override val color: String?,
    override var coordinate: Array<Int>,
    override val board: Board,
    override val name: String = "pawn"
) : Piece {

    var canPassant = false  //Чи можна взяти пішку на проході

    // TODO заміна пішки на останній лінії
    // TODO заборонити перскакування
    // TODO не можна бити свій колір
    override fun isMove(cord: Array<Int>): Boolean {
        if (coordinate contentEquals cord) {
            return false
        } else {
            var dist = 0
            if (color == "white") {
                dist = cord[0] - coordinate[0]
            }
            if (color == "black") {
                dist = coordinate[0] - cord[0]
            }
            if (coordinate[1].equals(cord[1])) { // рух прямо
                if (dist == 1 || (dist == 2 && ((coordinate[0] == 1 && color == "white") ||  // якщо рух на одну клітинку або на дві при першому русі
                            (coordinate[0] == 6 && color == "black")))
                ) {                    // (з 1-ї лінії для білої та 6 для чорної)
                    if (board.boardPiece[cord[0]]?.get(cord[1]) == null ) {   //якщо клітинка пуста
                        coordinate = cord
                        if (dist == 2) {
                            board.passant = this
                        }
                        return true
                    }
                }
            } else if ((coordinate[1] - cord[1]).absoluteValue == 1) { // рух по діагоналі
                if (dist == 1) {
                    if (board.boardPiece[cord[0]]?.get(cord[1]) != null) { // пішак б'є по діагоналі
                        coordinate = cord
                        return true
                    } else {  //взяття на проході
                        val pass = board.passant?.coordinate?.get(1) ?: -1
                        if (cord[1] == pass) {
                            val passCoord = board.passant?.coordinate
                            board.boardPiece[passCoord?.get(0)!!]?.set(pass, null)
                            board.specialMove[0] = board.passant?.coordinate!!
                            board.passant == null
                            coordinate = cord
                            return true
                        }
                    }
                }
            }
        }
        return false
    }
}
