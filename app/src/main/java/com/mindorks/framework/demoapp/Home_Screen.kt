package com.mindorks.framework.demoapp

import android.content.ComponentName
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import androidx.fragment.app.FragmentTransaction

class Home_Screen : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var drawer_layout: DrawerLayout
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home__screen)

        Log.d("MainActivity", "ONCREATE");
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, Favourites()).commit()

        drawer_layout = findViewById<DrawerLayout>(R.id.nav_view)
        toolbar= findViewById<Toolbar>(R.id.toolbar)

        val navigationView = findViewById<NavigationView>(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener(this)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,Home())

        val toggle = ActionBarDrawerToggle(this,drawer_layout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState==null){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,Home()).commit()
            navigationView.setCheckedItem(R.id.home)
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.home -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container,Home()).commit()
            R.id.favourite -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container,Favourites()).commit()
            R.id.recent_search -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container,Recent_Search()).commit()
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START))
        {
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        else {
            super.onBackPressed()
        }
    }
}

