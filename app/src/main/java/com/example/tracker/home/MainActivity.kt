package com.example.tracker

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation();
    }


    private fun setupToolbar(){
        val toolBar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolBar)
    }

    private fun setupNavigation(){
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
      /*  val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)*/

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        val toolBar: Toolbar = findViewById(R.id.toolbar)
        toolBar.setupWithNavController(navController, appBarConfiguration)
        setSupportActionBar(toolBar)
        //setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp() =
        findNavController(R.id.nav_host_fragment).navigateUp()

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.top_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        /*// Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.action_cut -> {
                text_view.text = "Cut"
                return true
            }
            R.id.action_copy -> {
                text_view.text = "Copy"
                return true
            }
            R.id.action_paste -> {
                text_view.text = "Paste"
                return true
            }
            R.id.action_new -> {
                text_view.text = "New"
                return true
            }
        }*/
        return super.onOptionsItemSelected(item)
    }
}
