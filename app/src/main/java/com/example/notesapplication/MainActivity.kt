package com.example.notesapplication

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    // Declaring the NavController
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setting up the toolbar if the action bar is not present
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Configuring the fragment name as an action bar
        navController = findNavController(R.id.fragmentContainerView)
        setupActionBarWithNavController(navController)

        // Handling the back press or back stack control
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Check if the current destination is the homeFragment
                if (navController.currentDestination?.id == R.id.homeFragment) {
                    // Finish the activity if on the homeFragment
                    finish()
                } else {
                    // Navigate up in the fragment hierarchy otherwise
                    navController.navigateUp()
                }
            }
        }

        // Adding the callback to the back button dispatcher
        onBackPressedDispatcher.addCallback(this, callback)
    }

    // Overriding the navigateUp action for proper fragment navigation
    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}
