package me.skrilltrax.notes.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import me.skrilltrax.notes.*
import me.skrilltrax.notes.databinding.ActivityMainBinding
import me.skrilltrax.notes.ui.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by lazy { ViewModelProvider(this).get(MainActivityViewModel::class.java) }
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
        router = MainActivityRouter(this, binding)
        setSupportActionBar(binding.bottomAppbar)
        setListeners()
    }

    private fun setListeners() {
        binding.fab.setOnClickListener {
            listener?.onFabClick()
            val currentDestination = navController.currentDestination
            if (currentDestination != null) {
                when (currentDestination.id) {
                    R.id.notesListFragment -> {
                        navController.navigate(R.id.noteFragment)
                        router.routeToNoteFragment()
                    }
                    R.id.noteFragment -> {
                        navController.popBackStack()
                        router.routeToNoteListFragment()
                    }
                }
            }
        }
    }

    fun bindListener(listener: MainActivityRouter.FabClickListener) {
        this.listener = listener
    }

    fun unbindListener(listener: MainActivityRouter.FabClickListener) {
        this.listener = null
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_note, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}
