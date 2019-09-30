package me.skrilltrax.notes

import androidx.appcompat.app.AppCompatActivity
import me.skrilltrax.notes.databinding.ActivityMainBinding

class MainActivityRouter(val activity: AppCompatActivity, private val binding: ActivityMainBinding) {
    fun routeToNoteFragment() {
        AnimUtils.animateFAB(binding.fab, R.drawable.ic_check_white_24dp)
        binding.bottomAppbar.replaceMenu(R.menu.menu_note)
    }
    fun routeToNoteListFragment() {
        AnimUtils.animateFAB(binding.fab, R.drawable.ic_add_white_24dp)
        binding.bottomAppbar.replaceMenu(R.menu.menu_note)
    }
}