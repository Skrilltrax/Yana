package me.skrilltrax.notes.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import me.skrilltrax.notes.ui.MainActivityRouter
import me.skrilltrax.notes.R
import me.skrilltrax.notes.databinding.ActivityMainBinding
import me.skrilltrax.notes.ui.BottomMenuFragment
import me.skrilltrax.notes.ui.fragments.NotesListFragment
import me.skrilltrax.notes.ui.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var host: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var router: MainActivityRouter
    lateinit var binding: ActivityMainBinding
    private var listener: MainActivityRouter.FabClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        host = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return
        navController = host.navController
        router = MainActivityRouter(binding)
        setSupportActionBar(binding.bottomAppbar)
        setListeners()
    }

    private fun setListeners() {
        binding.fab.setOnClickListener {
            if (listener != null) {
                if (listener?.onFabClick() == true) {
                    router.handleNavigation(navController)
                }
            } else {
                router.handleNavigation(navController)
            }
        }
    }

    fun bindListener(listener: MainActivityRouter.FabClickListener) {
        this.listener = listener
    }

    fun unbindListener() {
        this.listener = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                BottomMenuFragment().show(supportFragmentManager, "Menu")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id != R.id.notesListFragment) {
            router.handleNavigation(navController)
        } else {
            if ((supportFragmentManager.fragments.first() as NotesListFragment).interceptBackPress) {
                (supportFragmentManager.fragments.first() as NotesListFragment).handleBackPress()
            }
            // Android Q activity leaks
            if (isTaskRoot) {
                finishAfterTransition()
            } else {
                super.onBackPressed()
            }
        }
    }
}
