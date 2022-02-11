package com.example.chess.UI

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.example.chess.R
import android.view.View.OnTouchListener



class WatchFragment : Fragment() {

    private var seconds: Long = 0
    private var add = 0
    private var tapCount = 0

    private lateinit var textMin: TextView
    private lateinit var textSec: TextView
    private lateinit var textAdd: TextView
    private lateinit var inputLayout: ViewGroup
    private lateinit var mainLayout: ViewGroup

    private lateinit var timeV1: TextView
    private lateinit var timeV2: TextView

    //private lateinit var But: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val layout: View = inflater.inflate(R.layout.fragment_watch, container, false)
        return inflater.inflate(R.layout.fragment_watch, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textMin = view.findViewById(R.id.editMinutes) as TextView
        textSec = view.findViewById(R.id.editSecond) as TextView
        textAdd = view.findViewById(R.id.editAdditionally) as TextView
        inputLayout = view.findViewById(R.id.inputLayout) as ViewGroup
        mainLayout = view.findViewById(R.id.main_layout) as ViewGroup
        timeV1 = view.findViewById(R.id.time1) as TextView
        timeV2 = view.findViewById(R.id.time2) as TextView

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onStart() {
        super.onStart()


        lateinit var time1: TimerOnClick
        lateinit var time2: TimerOnClick
        var Y_coord = 0.0f

        mainLayout.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {

                /*
                Toast.makeText(activity, "ACTION_DOWN", Toast.LENGTH_SHORT).show()
                Y = event.y
                */
                Y_coord = event.y
                when {
                    tapCount == 0 -> {
                        textMin.visibility = View.INVISIBLE
                        textSec.visibility = View.INVISIBLE
                        textAdd.visibility = View.INVISIBLE
                        inputLayout.setBackgroundResource(R.color.light)
                        timeV1.setTextColor(resources.getColor(R.color.dark))
                        timeV2.setTextColor(resources.getColor(R.color.dark))
                        timeV1.setTextSize(
                            TypedValue.COMPLEX_UNIT_PX,
                            resources.getDimension(R.dimen.watchSize)
                        )

                        seconds =
                            textMin.text.toString().toLong() * 60 + textSec.text.toString().toInt()
                        add = textAdd.text.toString().toInt()
                        timeV1.rotation = 180F
                        time1 = TimerOnClick(timeV1, seconds, add)
                        time2 = TimerOnClick(timeV2, seconds, add)
                        time2.start()
                    }
                    tapCount % 2 == 1 -> {
                        changePlayer(time2, time1, timeV2, timeV2)
                    }
                    tapCount % 2 == 0 -> {
                        changePlayer(time1, time2, timeV1, timeV2)
                    }
                }
                tapCount++
                if (time1.isFinish || time2.isFinish) {
                    restart(time1, time2)
                }
            } else if (event.action == MotionEvent.ACTION_MOVE) {
                //val a = X_coord - event.x
                //Toast.makeText(activity, a.toString(), Toast.LENGTH_SHORT).show()
                if ((Y_coord - event.y) > 10) {
                    //Toast.makeText(activity, "ACTION_MOVE", Toast.LENGTH_SHORT).show()
                    restart(time1, time2)
                }
            }
            true
        }
    }

    private fun changePlayer(t1: TimerOnClick, t2: TimerOnClick, v1: TextView, v2: TextView) {
        t1.pause()
        t2.resume()
        mainLayout.setBackgroundResource(R.color.light)
        inputLayout.setBackgroundResource(R.color.light)
        v1.setTextColor(resources.getColor(R.color.dark))
        v2.setTextColor(resources.getColor(R.color.dark))
    }

    private fun restart(t1: TimerOnClick, t2: TimerOnClick) {
        t1.pause()
        t2.pause()
        timeV1.text = ""
        timeV2.text = ""
        textMin.visibility = View.VISIBLE
        textSec.visibility = View.VISIBLE
        textAdd.visibility = View.VISIBLE
        mainLayout.setBackgroundResource(R.color.light)
        inputLayout.setBackgroundResource(R.color.dark)

        tapCount = 0
    }
}
