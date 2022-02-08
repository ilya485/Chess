package com.example.chess.UI

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import com.example.chess.R


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.nav_open_drawer,
            R.string.nav_close_drawer
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
        val fragment: Fragment = StatisticFragment()
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.content_frame, fragment)
        ft.commit()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        var fragment: Fragment? = null
        var intent: Intent? = null

        //TODO raplace icons in layout
        when (id) {
            R.id.nav_together -> fragment = PvpFragment()
            R.id.nav_online -> fragment = OnlineFragment()
            R.id.nav_watch -> fragment = WatchFragment()
            R.id.nav_stat -> fragment = StatisticFragment()
            R.id.nav_instruction -> fragment = InstructionFragment()
            else -> fragment = PvpFragment()
        }
        if (fragment != null) {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.content_frame, fragment)
            ft.commit()
        } else {
            startActivity(intent)
        }
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}