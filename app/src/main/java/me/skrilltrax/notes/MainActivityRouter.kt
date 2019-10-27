package me.skrilltrax.notes

import androidx.navigation.NavController
import me.skrilltrax.notes.databinding.ActivityMainBinding

class MainActivityRouter(private val binding: ActivityMainBinding) {

    private fun routeToNoteFragment() {
        AnimUtils.animateFAB(binding.fab, R.drawable.ic_check_white_24dp)
        binding.bottomAppbar.replaceMenu(R.menu.menu_note)
    }
    private fun routeToNoteListFragment() {
        AnimUtils.animateFAB(binding.fab, R.drawable.ic_add_white_24dp)
        binding.bottomAppbar.replaceMenu(R.menu.menu_empty)
    }

    fun handleNavigation(navController: NavController) {
        val currentDestination = navController.currentDestination
        if (currentDestination != null) {
            when (currentDestination.id) {
                R.id.notesListFragment -> {
                    navController.navigate(R.id.noteFragment)
                    routeToNoteFragment()
                    binding.bottomAppbar.performShow()
                }
                R.id.noteFragment -> {
                    navController.navigateUp()
                    routeToNoteListFragment()
                }
            }
        }
    }

    interface FabClickListener {
        fun onFabClick(): Boolean
    }
}
