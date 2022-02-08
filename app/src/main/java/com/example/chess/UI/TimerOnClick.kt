package com.example.chess.UI

import android.os.CountDownTimer
import android.widget.TextView
import com.example.chess.R
import java.lang.String
import java.util.*

class TimerOnClick(
    val tView: TextView,
    var time: Long,
    val add: Int,

    //val timeV2: TextView,
) {
    init {
        setTimeView(time)
    }
    private lateinit var timer: CountDownTimer
    var isFinish = false

    fun start() {
        timer = object : CountDownTimer(time * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                //millisUntilFinished += timeInPause
                time = millisUntilFinished / 1000
                setTimeView(time)
                //timeV2.text = (millisUntilFinished / 1000)
            }

            override fun onFinish() {
                isFinish = true
                tView.setText(R.string.timeIsOut)
            }
        }.start()
    }

    fun resume() {
        start()
        //timer.onResume()
    }

    //fun setFinish(){ isFinish = true}

    fun pause() {
        timer.cancel()
        time += add
        setTimeView(time)

        //var Timer: TimerOnClick = TimerOnClick(tView, time, add)
    }

    private fun setTimeView(time: Long) {
        //tView.textSize = 14F
        tView.text = String.format(
            Locale.getDefault(),
            "%02d:%02d", time % 3600 / 60, time % 60
        )
    }
}