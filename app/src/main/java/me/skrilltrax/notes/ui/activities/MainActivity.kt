package me.skrilltrax.notes.ui.activities

import android.animation.Animator
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.services.drive.Drive
import kotlinx.coroutines.*
import me.skrilltrax.notes.*
import me.skrilltrax.notes.databinding.ActivityMainBinding
import me.skrilltrax.notes.ui.fragments.NotesListFragment
import me.skrilltrax.notes.ui.viewmodel.MainActivityViewModel
import org.jetbrains.anko.imageResource

class MainActivity : AppCompatActivity(), View.OnClickListener, NavController.OnDestinationChangedListener {

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
        navController.addOnDestinationChangedListener(this)
        setSupportActionBar(binding.bottomAppbar)
//        setObservers()
        setListeners()
    }

    fun setListeners() {
        binding.fab.setOnClickListener(this)
    }

    private fun changeToNoteFragment() {
        AnimUtils.animateFAB(binding.fab, R.drawable.ic_check_white_24dp)
//        binding.bottomAppbar.replaceMenu()
    }

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
        when (destination.id) {
            R.id.noteFragment -> {
                changeToNoteFragment()
            }
        }
    }

    override fun onClick(view: View?) {
        val currentDestination = navController.currentDestination
        if (currentDestination != null) {
            when (currentDestination.id) {
                R.id.notesListFragment -> {
                    navController.navigate(R.id.noteFragment)
                    changeToNoteFragment()
                }
                R.id.noteFragment -> {
                    viewModel.saveNote()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        when (host.navController.currentDestination?.id) {
            R.id.noteFragment -> {
//                binding.bottomAppbar.replaceMenu()
            }
            R.id.notesListFragment -> {
                menuInflater.inflate(R.menu.menu_note, menu)
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}
