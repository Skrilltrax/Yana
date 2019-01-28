package me.skrilltrax.notes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(list: ArrayList<NoteData>?) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

    private var list: ArrayList<NoteData>? = null
    init {

        this.list = list
        Log.e(TAG,list.toString())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_layout,parent,false)
        return MyViewHolder(view, list)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d(TAG,"In OnBindViewHolder")
        holder.titleTextView.text = list?.get(position)?.titleText
        holder.detailTextView.text = list?.get(position)?.detailText

    }

    class MyViewHolder(itemView: View, list: ArrayList<NoteData>?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var titleTextView: TextView = itemView.findViewById(R.id.note_title)
        var detailTextView: TextView = itemView.findViewById(R.id.note_detail)
        private var list: ArrayList<NoteData>?
        init {
            itemView.setOnClickListener(this)
            this.list = list
        }

        override fun onClick(v: View?) {
            Log.d(TAG,"In MyViewHolder")
            val itemPosition: Int = adapterPosition
            val bundle = Bundle()
            bundle.putString("title", list?.get(itemPosition)?.titleText)
            bundle.putString("detail", list?.get(itemPosition)?.detailText)

            val intent = Intent(v?.context,NoteActivity::class.java)
            intent.putExtras(bundle)
            v?.context?.startActivity(intent)
        }

         companion object {
             private val TAG: String = MyViewHolder::class.java.simpleName
         }

    }

    fun updateDataSet(context: Context) {

        list = Utils.getNotes(context)
        notifyDataSetChanged()
    }

    companion object {
        private val TAG: String = CustomAdapter::class.java.simpleName
    }

}