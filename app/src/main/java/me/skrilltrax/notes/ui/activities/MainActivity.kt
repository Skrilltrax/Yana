package me.skrilltrax.notes.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.services.drive.Drive
import kotlinx.coroutines.*
import me.skrilltrax.notes.*
import me.skrilltrax.notes.databinding.ActivityMainBinding
import me.skrilltrax.notes.ui.fragments.NotesListFragment
import me.skrilltrax.notes.ui.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val viewModel: MainActivityViewModel by lazy { ViewModelProvider(this).get(MainActivityViewModel::class.java) }
    private lateinit var binding: ActivityMainBinding
    private lateinit var host: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        host = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return
        navController = host.navController
        setObservers()
        setListeners()
    }

    private fun setListeners() {
        binding.fab.setOnClickListener {
            navController.navigate(R.id.noteFragment)
        }

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.noteFragment -> {
                    binding.fab.setImageResource(R.drawable.ic_check_white_24dp)
                }
            }
        }
    }

    private fun setObservers() {

    }

    override fun onClick(view: View?) {
        val currentDestination = navController.currentDestination
        if (currentDestination != null) {
            when (currentDestination.id) {
                R.id.nav_host_fragment -> {

                }
                R.id.noteFragment -> {

                }
            }
        }
    }
}

