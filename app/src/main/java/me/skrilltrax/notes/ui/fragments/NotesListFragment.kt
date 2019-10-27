package me.skrilltrax.notes.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import me.skrilltrax.notes.R
import me.skrilltrax.notes.adapter.NoteListAdapter
import me.skrilltrax.notes.databinding.FragmentNotesListBinding
import me.skrilltrax.notes.ui.viewmodel.MainActivityViewModel

class NotesListFragment : Fragment() {

    private val mainActivityViewModel by viewModels<MainActivityViewModel>()
    lateinit var binding: FragmentNotesListBinding

    override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notes_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        mainActivityViewModel.getAllNotes()
    }

    private fun setObservers() {
        mainActivityViewModel.notesList.observe(this, Observer {
            binding.recyclerView.adapter = NoteListAdapter(it)
        })
    }

    companion object {

        fun newInstance(): NotesListFragment {
            return NotesListFragment()
        }
    }
}
