package com.example.chess

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.p2p.WifiP2pManager
import com.example.chess.UI.MainActivity

class WiFiDirectBroadcastReceiver(
    private val manager: WifiP2pManager,
    private val channel: WifiP2pManager.Channel,
    private val activity: MainActivity
) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        TODO("Not yet implemented")
    }
}