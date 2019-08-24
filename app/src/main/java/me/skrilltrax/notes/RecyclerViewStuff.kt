package me.skrilltrax.notes

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.skrilltrax.notes.adapter.CustomAdapter
import me.skrilltrax.notes.databinding.ActivityMainBinding
import me.skrilltrax.notes.model.NoteData

class RecyclerViewStuff(binding: ActivityMainBinding) {

    companion object {
        lateinit var recyclerView: RecyclerView
        lateinit var adapter: CustomAdapter

    }
    private var list: ArrayList<NoteData>?
    private var layoutManager: LinearLayoutManager

    init {
        recyclerView = binding.recyclerView
        layoutManager = LinearLayoutManager(binding.root.context)
        list = Utils.getNotes(binding.root.context)
        adapter = CustomAdapter(list)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        Log.e("RecyclerViewStuff",list.toString())
    }


}