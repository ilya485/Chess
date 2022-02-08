package com.example.chess.UI

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.chess.Game_logic.Board
import com.example.chess.R
import java.util.*


class PvpFragment : Fragment() {

    private var pos_now = Array(2) { -1 }
    private var isGame = false
    private var seconds = 0
    private var verticalCoordinate = arrayOf("a", "b", "c", "d", "e", "f", "g", "h")

    private val running = false
    private val wasRunning = false
    private var isRule = true
    private var isTime = false
    private var halfMoveCount = 1
    private var leftTime1: Long = 0
    private var leftTime2: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val layout: View = inflater.inflate(R.layout.fragment_pvp, container, false)
        return inflater.inflate(R.layout.fragment_pvp, container, false)

    }

    override fun onStart() {
        super.onStart()

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


        val view = view

        if (view != null) {

            val im = view.findViewById(Board_id[0][0]) as ImageView
            var color = im.background

            val startButton: Button = view.findViewById<View>(R.id.start_button) as Button
            var sRule: Switch = view.findViewById(R.id.rule)
            var sTime: Switch = view.findViewById(R.id.timeView)
            val textMin = view.findViewById(R.id.editMinutes) as TextView
            val textSec = view.findViewById(R.id.editSecond) as TextView
            val textAdd = view.findViewById(R.id.editAdditionally) as TextView

            var tableView = emptyArray<Array<ImageView>>()

            for (row in Board_id) {
                var a = emptyArray<ImageView>()
                for (item in row) {
                    a += view.findViewById<ImageView>(item) as ImageView
                }
                tableView += a
            }



            sTime.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    textMin.visibility = View.VISIBLE
                    textSec.visibility = View.VISIBLE
                    textAdd.visibility = View.VISIBLE
                    isTime = true
                } else {
                    textMin.visibility = View.INVISIBLE
                    textSec.visibility = View.INVISIBLE
                    textAdd.visibility = View.INVISIBLE
                    isTime = false
                }
            }

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

            startButton.setOnClickListener {
                if (isGame) {
                    isGame = false
                    startButton.setText(R.string.start)
                } else {
                    isGame = true
                    startButton.setText(R.string.restart)
                    //for (i in )  //TODO Restart game

                }
                if (isTime) {

                    /*seconds = textMin.text.toString().toInt() * 60 + textSec.text.toString().toInt()
                    textMin.visibility=View.INVISIBLE
                    textSec.visibility=View.INVISIBLE
                    textAdd.visibility=View.INVISIBLE
                    Board(Board_id)
                     */


                    /*leftTime1= seconds.toLong()
                    leftTime2= seconds.toLong()
                    //startTime = System.nanoTime() / 1000000000
                    var leftTime:Long=0
                    var timeView:TextView?
                    if(halfMoveCount%2==0){
                        timeView = view.findViewById<View>(R.id.time_view) as TextView
                        leftTime = leftTime2
                    } else{
                        timeView = view.findViewById<View>(R.id.time_view2) as TextView
                        leftTime = leftTime1
                    }
                    val startTime = System.nanoTime()/1000000000
                    val handler = Handler()*/

                    /*Раз в певний проміжок часу перевіряємо чий хід
                    запам'ятовуємо час зміни ходу
                    обчислюємо тривалість ходу
                     */

                    val timeView = view.findViewById<View>(R.id.time_view) as TextView
                    val timeView2 = view.findViewById<View>(R.id.time_view2) as TextView
                    var time:String = java.lang.String.format(
                        Locale.getDefault(),
                        "%02d:%02d", 5, 35
                    )
                    timeView.setText(time)
                    time = java.lang.String.format(
                        Locale.getDefault(),
                        "%02d:%02d", 4, 26
                    )
                    timeView2.setText(time)

                    //TODO вивести логіку годинника в окремий клас
                    var previousPlayer = halfMoveCount

                    /*handler.post(object : Runnable {
                        override fun run() {
                            if (halfMoveCount!=previousPlayer){
                                previousPlayer++

                            }
                            val currrentTime = System.nanoTime() / 1000000000
                            val left: Long = leftTime - (currrentTime - startTime)
                            val minutes: Long = left % 3600 / 60
                            val secs: Long = left % 60

                            val time: String = java.lang.String.format(
                                Locale.getDefault(),
                                "%02d:%02d", minutes, secs
                            )
                            timeView.setText(time)
                            handler.postDelayed(this, 1000)
                        }
                    })*/
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

                            } else if (isRule) {
                                if (b.moveRule(arrayOf(pos_now, posNext)) == true) {
                                    val image1 = tableView[pos_now[0]][pos_now[1]]
                                    val image2 = tableView[posNext[0]][posNext[1]]
                                    image2.setImageDrawable(image1.drawable)
                                    if (posNext[0] != 9 || posNext[0] != 8) {
                                        image1.setImageDrawable(null)
                                    }

                                    if (b.specialMove[0][0] != -1 && b.specialMove[0][1] != -1 ){  // special move
                                        if(b.specialMove[1][0]==-1){
                                            tableView[b.specialMove[0][0]][b.specialMove[0][1]].setImageDrawable(null)

                                        }
                                    }
                                    pos_now = arrayOf(-1, -1)
                                    image1.background = color
                                } else {
                                    val image = tableView[pos_now[0]][pos_now[1]]
                                    image.background = color
                                    pos_now = arrayOf(-1, -1)
                                }
                            } else {
                                b.moveWithoutRule(arrayOf(pos_now, posNext))
                                //TODO add rules
                                val image1 = tableView[pos_now[0]][pos_now[1]]
                                val image2 = tableView[posNext[0]][posNext[1]]

                                if (posNext[0] == 9 || posNext[0] == 8) {
                                    image1.setImageDrawable(null)
                                }else if (pos_now[0] == 9 || pos_now[0] == 8){
                                    image2.setImageDrawable(image1.drawable)
                                } else{
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
            }
        }
    }
    //TODO SaveInstanceState
}