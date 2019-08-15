package me.skrilltrax.notes

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.skrilltrax.notes.adapter.CustomAdapter
import me.skrilltrax.notes.model.NoteData

class RecyclerViewStuff(activity: AppCompatActivity){

    companion object {
        lateinit var recyclerView: RecyclerView
        lateinit var adapter: CustomAdapter

    }
    private var list: ArrayList<NoteData>?
    private var layoutManager: LinearLayoutManager

    init {
        recyclerView = activity.findViewById(R.id.recycler_view)
        layoutManager = LinearLayoutManager(activity)
        list = Utils.getNotes(activity)
        adapter = CustomAdapter(list)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        Log.e("RecyclerViewStuff",list.toString())
    }


}