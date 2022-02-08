package com.example.chess.UI

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.chess.DataBaseHelper
import com.example.chess.R


class StatisticFragment : Fragment() {

    //private val db: SQLiteDatabase? = null
    private var cursor: Cursor? = null
    private var pos_now = Array(2) { -1 }
    private var isRule = true
    // TODO: Rename and change types of parameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistic, container, false)
    }

    override fun onStart() {
        super.onStart()
        val view = view
        val databaseHelper: SQLiteOpenHelper = DataBaseHelper(activity)
        val Board_id = arrayOf(
            arrayOf(R.id.a1, R.id.b1, R.id.c1, R.id.d1, R.id.e1, R.id.f1, R.id.g1, R.id.h1),
            arrayOf(R.id.a2, R.id.b2, R.id.c2, R.id.d2, R.id.e2, R.id.f2, R.id.g2, R.id.h2),
            arrayOf(R.id.a3, R.id.b3, R.id.c3, R.id.d3, R.id.e3, R.id.f3, R.id.g3, R.id.h3),
            arrayOf(R.id.a4, R.id.b4, R.id.c4, R.id.d4, R.id.e4, R.id.f4, R.id.g4, R.id.h4),
            arrayOf(R.id.a5, R.id.b5, R.id.c5, R.id.d5, R.id.e5, R.id.f5, R.id.g5, R.id.h5),
            arrayOf(R.id.a6, R.id.b6, R.id.c6, R.id.d6, R.id.e6, R.id.f6, R.id.g6, R.id.h6),
            arrayOf(R.id.a7, R.id.b7, R.id.c7, R.id.d7, R.id.e7, R.id.f7, R.id.g7, R.id.h7),
            arrayOf(R.id.a8, R.id.b8, R.id.c8, R.id.d8, R.id.e8, R.id.f8, R.id.g8, R.id.h8),
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

        if (view !== null) {
            val listGames: ListView = view.findViewById(R.id.list_games) as ListView
            val db = databaseHelper.readableDatabase


            val im = view?.findViewById(Board_id[0][0]) as ImageView
            var color = im.background
            var tableView = emptyArray<Array<ImageView>>()
            for (row in Board_id) {
                var a = emptyArray<ImageView>()
                for (item in row) {
                    a += view.findViewById<ImageView>(item) as ImageView
                }
                tableView += a
            }


            var sRule: Switch = view.findViewById(R.id.rule)
            sRule.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    isRule = !isChecked
                    for (a in tableView[8]) {
                        a.visibility = View.VISIBLE
                    }
                    for (a in tableView[9]) {
                        a.visibility = View.VISIBLE
                    }
                } else {
                    isRule = !isChecked
                    for (a in tableView[8]) {
                        a.visibility = View.INVISIBLE
                    }
                    for (a in tableView[9]) {
                        a.visibility = View.INVISIBLE
                    }
                }
            }


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
                        /* Створити глобальну змінну, що міститиме координати,
                        *   якщо вона пуста натиснення перше
                        *               перевірка на непусту клітинку і очікування другого натискання
                        *   якщо не пуста тоді натскання друге і фігура якою ходитимуть вже виділена
                        *           перевірка правильності ходу
                        *           зіміна зображення*/

                        //val a = view.findViewById(Board[row][col]) as ImageView
                        //val text = "" + row + col
                        //val duration = Toast.LENGTH_SHORT

                        //val toast = Toast.makeText(activity, text, duration)
                        //toast.show()

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

                        } /*else if (isRule) {
                            if (b.moveRule(arrayOf(pos_now, posNext)) == true) {
                                val image1 = tableView[pos_now[0]][pos_now[1]]
                                val image2 = tableView[posNext[0]][posNext[1]]
                                image2.setImageDrawable(image1.drawable)
                                if (posNext[0] != 9 || posNext[0] != 8) {
                                    image1.setImageDrawable(null)
                                }

                                if (b.specialMove[0][0] != -1 && b.specialMove[0][1] != -1) {  // special move
                                    if (b.specialMove[1][0] == -1) {
                                        tableView[b.specialMove[0][0]][b.specialMove[0][1]].setImageDrawable(
                                            null
                                        )

                                    }
                                }
                                pos_now = arrayOf(-1, -1)
                                image1.background = color
                            } else {
                                val image = tableView[pos_now[0]][pos_now[1]]
                                image.background = color
                                pos_now = arrayOf(-1, -1)
                            }
                        }*/ else {
                            //b.moveWithoutRule(arrayOf(pos_now, posNext))
                            //TODO add rules
                            val image1 = tableView[pos_now[0]][pos_now[1]]
                            val image2 = tableView[posNext[0]][posNext[1]]

                            if (posNext[0] == 9 || posNext[0] == 8) {
                                image1.setImageDrawable(null)
                            } else if (pos_now[0] == 9 || pos_now[0] == 8) {
                                image2.setImageDrawable(image1.drawable)
                            } else {
                                image2.setImageDrawable(image1.drawable)
                                image1.setImageDrawable(null)
                            }
                            pos_now = arrayOf(-1, -1)
                            image1.background = color
                            //Board.new_pos(pos, arrayOf(row, col))
                            //(pos[0] != -1 && pos[1] != -1 && row<8 && col<8) {
                        }
                    }
                }
            }




            try {

                cursor = db.query(
                    "GAME", arrayOf("_id", "NOTE", "WINNER"),
                    null, null, null, null, null
                )
                val noteValues = ContentValues()
                noteValues.put("NOTE", "d4:f6; f3:c5; f4:d4")
                noteValues.put("WINNER", "White")
                //db.insert("GAME", null, noteValues)
                val listAdapter = SimpleCursorAdapter(
                    activity,
                    android.R.layout.simple_list_item_1,
                    cursor, arrayOf("NOTE"), intArrayOf(android.R.id.text1),
                    0
                )
                listGames.setAdapter(listAdapter)
            } catch (e: SQLiteException) {
                val toast: Toast =
                    Toast.makeText(activity, "Database unavailable", Toast.LENGTH_SHORT)
                toast.show()
            }
        }

    }
}

