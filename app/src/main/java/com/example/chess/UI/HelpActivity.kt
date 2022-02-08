package com.example.chess.UI

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.chess.R

class HelpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
        setSupportActionBar(findViewById(R.id.toolbar))
    }
}