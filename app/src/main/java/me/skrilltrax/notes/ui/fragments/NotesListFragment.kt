package me.skrilltrax.notes.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.bottomappbar.BottomAppBar
import me.skrilltrax.notes.R
import me.skrilltrax.notes.adapter.NoteListAdapter
import me.skrilltrax.notes.databinding.FragmentNotesListBinding
import me.skrilltrax.notes.interfaces.BackInterface
import me.skrilltrax.notes.ui.activities.MainActivity
import me.skrilltrax.notes.ui.viewmodel.MainActivityViewModel

class NotesListFragment : Fragment(), BackInterface {

    private val mainActivityViewModel by viewModels<MainActivityViewModel>()
    private lateinit var parentActivity: MainActivity
    private lateinit var bottomAppBar: BottomAppBar
    lateinit var binding: FragmentNotesListBinding
    var interceptBackPress = false

    private fun setObservers() {
        mainActivityViewModel.notesList.observe(this, Observer {
            binding.recyclerView.adapter = NoteListAdapter(it)
        })
    }

    fun handleBackPress() {
        Log.d("NotesList", "handleBackPress")
        if (interceptBackPress) {
            bottomAppBar.navigationIcon =
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_menu_24dp)
            interceptBackPress = false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        parentActivity = activity as MainActivity
        bottomAppBar = parentActivity.binding.bottomAppbar
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notes_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        mainActivityViewModel.getAllNotes()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("NotesList", "selected ${item.itemId}")
        when (item.itemId) {
            R.id.favourites -> {
                item.isChecked = true
                interceptBackPress = true
                bottomAppBar.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_arrow_back)
                return true
            }
            R.id.search -> {
                item.isChecked = true
                interceptBackPress = false
                bottomAppBar.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_arrow_back)
                return true
            }
        }
        return false
    }

    override fun onBackPressed() {
        handleBackPress()
    }

    companion object {
        fun newInstance(): NotesListFragment {
            return NotesListFragment()
        }
    }
}
