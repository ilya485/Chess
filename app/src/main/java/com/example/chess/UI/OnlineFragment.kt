package com.example.chess.UI

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.chess.Game_logic.Board
import com.example.chess.R


class OnlineFragment : Fragment() {
    private var pos_now = Array(2) { -1 }
    private var note = ""
    private var colName = arrayOf("a", "b", "c", "d", "e", "f", "g", "h")
    private var isGame=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val layout: View = inflater.inflate(R.layout.fragment_online, container, false)
        return inflater.inflate(R.layout.fragment_online, container, false)

    }

    override fun onStart() {
        super.onStart()

        val Board_id = arrayOf(
            arrayOf(R.id.a1, R.id.a2, R.id.a3, R.id.a4, R.id.a5, R.id.a6, R.id.a7, R.id.a8),
            arrayOf(R.id.b1, R.id.b2, R.id.b3, R.id.b4, R.id.b5, R.id.b6, R.id.b7, R.id.b8),
            arrayOf(R.id.c1, R.id.c2, R.id.c3, R.id.c4, R.id.c5, R.id.c6, R.id.c7, R.id.c8),
            arrayOf(R.id.d1, R.id.d2, R.id.d3, R.id.d4, R.id.d5, R.id.d6, R.id.d7, R.id.d8),
            arrayOf(R.id.e1, R.id.e2, R.id.e3, R.id.e4, R.id.e5, R.id.e6, R.id.e7, R.id.e8),
            arrayOf(R.id.f1, R.id.f2, R.id.f3, R.id.f4, R.id.f5, R.id.f6, R.id.f7, R.id.f8),
            arrayOf(R.id.g1, R.id.g2, R.id.g3, R.id.g4, R.id.g5, R.id.g6, R.id.g7, R.id.g8),
            arrayOf(R.id.h1, R.id.h2, R.id.h3, R.id.h4, R.id.h5, R.id.h6, R.id.h7, R.id.h8),
            arrayOf(
                R.id.black_king,
                R.id.black_queen,
                R.id.black_bishop,
                R.id.black_knight,
                R.id.black_rock,
                R.id.black_pawn
            ),
            arrayOf(
                R.id.white_king,
                R.id.white_queen,
                R.id.white_bishop,
                R.id.white_knight,
                R.id.white_rock,
                R.id.white_pawn
            )
        )


        val view = view

        if (view != null) {
            val startButton: Button = view.findViewById<View>(R.id.start_button) as Button
            val noteView = view.findViewById<TextView>(R.id.notation)

            val im = view.findViewById(Board_id[0][0]) as ImageView
            var color = im.background

            var tableView = emptyArray<Array<ImageView>>()
            for (row in Board_id) {
                var a = emptyArray<ImageView>()
                for (item in row) {
                    a += view.findViewById<ImageView>(item) as ImageView
                }
                tableView += a
            }

            if (isGame) {
                isGame = false
                //startButton.setText(R.string.start)
            } else {
                isGame = true
                //.setText(R.string.restart)
                //for (i in )  //TODO Code restart game

            }
            var b = Board()

            for (row in tableView) {
                for (item in row) {
                    val title = item
                    title.setOnClickListener {
                        var posNext = arrayOf(-1, -1)

                        for (a in tableView) {
                            if (a.contains(item)) {
                                posNext[1] = a.indexOf(item)
                                posNext[0] = tableView.indexOf(a)
                            }
                        }

                        if (pos_now[0] == -1 && pos_now[1] == -1) {
                            val image = tableView[posNext[0]][posNext[1]]
                            if (image.drawable != null) {
                                pos_now = posNext
                                color = image.background
                                image.setBackgroundResource(R.color.dedicated)
                            }

                        } else if (pos_now[0] == posNext[0] && pos_now[1] == posNext[1]) {
                            val image = tableView[pos_now[0]][pos_now[1]]
                            image.background = color
                            pos_now = arrayOf(-1, -1)

                        } else
                            if (b.moveRule(arrayOf(pos_now, posNext)) == true) {
                                val image1 = tableView[pos_now[0]][pos_now[1]]
                                val image2 = tableView[posNext[0]][posNext[1]]
                                image2.setImageDrawable(image1.drawable)
                                if (posNext[0] != 7 && posNext[0] != 8) {
                                    image1.setImageDrawable(null)
                                }
                                note += colName[pos_now[0] ] + (pos_now[1] + 1) + " : " +
                                        colName[posNext[0] ] + (posNext[1] +1) + "; "
                                noteView.text = note

                                pos_now = arrayOf(-1, -1)
                                image1.background = color


                            } else {
                                val image = tableView[pos_now[0]][pos_now[1]]
                                image.background = color
                                pos_now = arrayOf(-1, -1)
                            }
                    }
                }
            }
        }
    }
}