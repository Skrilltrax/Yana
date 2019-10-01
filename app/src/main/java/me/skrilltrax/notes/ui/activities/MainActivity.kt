package me.skrilltrax.notes.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import me.skrilltrax.notes.*
import me.skrilltrax.notes.databinding.ActivityMainBinding
import me.skrilltrax.notes.ui.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var host: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var router: MainActivityRouter
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
        binding.appbar.setLifted(false)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_note, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id != R.id.notesListFragment) {
            router.handleNavigation(navController)
        } else {
            //Android Q activity leaks
            if (isTaskRoot) {
                finishAfterTransition()
            } else {
                super.onBackPressed()
            }
        }
    }
}
