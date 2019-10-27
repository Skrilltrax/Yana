package me.skrilltrax.notes.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import me.skrilltrax.notes.utils.PreferenceUtils
import me.skrilltrax.notes.ui.MainActivityRouter
import me.skrilltrax.notes.R
import me.skrilltrax.notes.databinding.FragmentNoteBinding
import me.skrilltrax.notes.data.model.NoteData
import me.skrilltrax.notes.ui.activities.MainActivity
import me.skrilltrax.notes.ui.viewmodel.MainActivityViewModel

class NoteFragment : Fragment(), MainActivityRouter.FabClickListener {
    private lateinit var binding: FragmentNoteBinding
    private val mainActivityViewModel by viewModels<MainActivityViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = FragmentNoteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("NoteFrag", "bind")
        (activity as MainActivity).bindListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        (activity as MainActivity).unbindListener()
        Log.d("NoteFrag", "unbind")
        super.onPause()
    }

    private fun checkNote(): Boolean {
        Log.d("checkNote", "Called")
        return when {
            binding.noteTitle.text.isNullOrEmpty() and binding.noteContent.text.isNullOrEmpty() -> {
                PreferenceUtils.showShortAnchoredSnackbar(binding.noteContent, R.id.fab, R.string.enter_title_detail)
                false
            }
            binding.noteTitle.text.isNullOrEmpty() -> {
                PreferenceUtils.showShortAnchoredSnackbar(binding.noteContent, R.id.fab, R.string.enter_title)
                false
            }
            binding.noteContent.text.isNullOrEmpty() -> {
                PreferenceUtils.showShortAnchoredSnackbar(binding.noteContent, R.id.fab, R.string.enter_detail)
                false
            }
            else -> true
        }
    }

    // Returns true if action onFabClick was successful
    override fun onFabClick(): Boolean {
        if (checkNote()) {
            val note = NoteData(-1, binding.noteTitle.text.toString(), binding.noteContent.text.toString())
            mainActivityViewModel.saveNote(note)
            return true
        }
        return false
    }
}
