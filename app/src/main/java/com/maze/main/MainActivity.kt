package com.maze.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.produceState
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.maze.R
import com.maze.databinding.ActivityMainBinding
import com.maze.ktorclient.ViolationService
import com.maze.notification.NotificationService
import java.util.Collections.emptyList
import com.maze.ktorclient.ViolationResponse


class MainActivity : AppCompatActivity() {
    private val TAG: String  = "MainActivity"
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val service = ViolationService.create()    //creating violation client

    @RequiresApi(33)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            val violations = produceState<List<ViolationResponse>>(
                initialValue = emptyList(),
                producer = {
                    value = service.getViolations()
                }

            )

        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        onNewIntent(intent)

        val notificationServiceIntent = Intent(applicationContext, NotificationService::class.java)
        startService(notificationServiceIntent)
    }

    override fun onNewIntent(intent: Intent?) {
        if (intent != null) {
            super.onNewIntent(intent)
        }
        val passedIntent = intent!!
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        if (passedIntent.hasExtra("learnMore")) {
            navController.navigate(R.id.settingsFragment)

        } else if (passedIntent.hasExtra("findParking")) {
            navController.navigate(R.id.SecondFragment)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {

                val navController = findNavController(R.id.nav_host_fragment_content_main)
                navController.navigate(R.id.settingsFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    companion object {
        const val notificationDescription = "This is a high ticket zone or restricted parking area"
        const val notificationLabel = "Possible Parking Violation Warning"
    }
}