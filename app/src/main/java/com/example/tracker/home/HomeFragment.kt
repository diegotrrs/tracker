package com.example.tracker.home

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.tracker.R
import com.example.tracker.home.settings.SettingsViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.home_toolbar.*

class HomeFragment : Fragment(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigation();
    }


    private fun setupNavigation(){
        val navController = findNavController(requireActivity(), R.id.nav_host_fragment)
        bottomNavigationView.setupWithNavController(navController)

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.top_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.miCompose -> {

            }
        }

        return super.onOptionsItemSelected(item)
    }




}