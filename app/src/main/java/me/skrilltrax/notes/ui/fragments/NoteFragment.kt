package me.skrilltrax.notes.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import me.skrilltrax.notes.MainActivityRouter
import me.skrilltrax.notes.databinding.FragmentNoteBinding
import me.skrilltrax.notes.ui.activities.MainActivity
import me.skrilltrax.notes.ui.viewmodel.MainActivityViewModel

class NoteFragment: Fragment(), MainActivityRouter.FabClickListener {
    private lateinit var binding: FragmentNoteBinding
    private val mainActivityViewModel by viewModels<MainActivityViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Frag", "bind")
        (activity as MainActivity).bindListener(this)
    }

    override fun onStop() {
        (activity as MainActivity).unbindListener()
        Log.d("Frag", "unbind")
        super.onStop()
    }

    override fun onFabClick() {
        mainActivityViewModel.saveNote()
    }
}