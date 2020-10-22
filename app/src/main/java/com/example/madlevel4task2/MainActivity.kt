package com.example.madlevel4task2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var activeMenu = R.menu.menu_history
    private lateinit var navController: NavController
    private lateinit var gameRepository: GameRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        navController = findNavController(R.id.nav_host_fragment)
        toolbar.setNavigationOnClickListener {
            startActivity(Intent(applicationContext, RPSOverviewFragment::class.java))
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(activeMenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.history_icon -> {
                item.isVisible = false
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                supportActionBar!!.setDisplayShowHomeEnabled(true)
                activeMenu = R.menu.menu_delete
                onCreateOptionsMenu(toolbar.menu)
                toolbar.title = "Your Game History"
                navController.navigate(
                    R.id.action_RPSOverviewFragment_to_GameHistoryFragment
                )
                return true
            }

            R.id.delete_icon -> {

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}