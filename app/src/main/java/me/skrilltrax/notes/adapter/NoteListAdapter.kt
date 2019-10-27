package me.skrilltrax.notes.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.skrilltrax.notes.adapter.NoteListAdapter.NoteViewHolder
import me.skrilltrax.notes.databinding.ItemNoteBinding
import me.skrilltrax.notes.model.NoteData

class NoteListAdapter(private val noteList: List<NoteData>) : RecyclerView.Adapter<NoteViewHolder>() {

    private lateinit var binding: ItemNoteBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemNoteBinding.inflate(inflater)
        return NoteViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(noteList[position])
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: NoteData) {
            Log.d("inViewHolder", "bind")
            binding.note = note
        }
    }
}
