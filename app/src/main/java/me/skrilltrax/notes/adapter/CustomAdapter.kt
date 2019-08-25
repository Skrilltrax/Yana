package me.skrilltrax.notes.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import me.skrilltrax.notes.R
import me.skrilltrax.notes.Utils
import me.skrilltrax.notes.model.NoteData
import me.skrilltrax.notes.ui.ModalSheet

class CustomAdapter(list: ArrayList<NoteData>?) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

    init {
        Companion.list = list
        Log.e(TAG,list.toString())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        list = Utils.getNotes(parent.context)
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_layout,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d(TAG,"In OnBindViewHolder")
        holder.titleTextView.text = list?.get(position)?.titleText
        holder.detailTextView.text = list?.get(position)?.detailText
//        holder.itemView.setOnLongClickListener(this)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener {

        var titleTextView: TextView = itemView.findViewById(R.id.note_title)
        var detailTextView: TextView = itemView.findViewById(R.id.note_detail)
        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        override fun onClick(v: View?) {

            val itemPosition: Int = adapterPosition
            val bundle = Bundle()
            bundle.putString("title", list?.get(itemPosition)?.titleText)
            bundle.putString("detail", list?.get(itemPosition)?.detailText)
            bundle.putInt("position",itemPosition)

            val intent = Intent(v?.context, NoteActivity::class.java)
            intent.putExtras(bundle)
            v?.context?.startActivity(intent)
        }

        override fun onLongClick(v: View?): Boolean {
            Log.e("LongClick","WOOORRRKKKK")
            val modalSheet = ModalSheet()
            modalSheet.show((v?.context as AppCompatActivity).supportFragmentManager,"MODALBOTTOMSHEET")
            return true
        }

         companion object {
             private val TAG: String = MyViewHolder::class.java.simpleName
         }

    }

    fun updateDataSet(context: Context) {

        list =
            Utils.getNotes(context)
        notifyDataSetChanged()
    }

    companion object {
        var list: ArrayList<NoteData>? = null
        private val TAG: String = CustomAdapter::class.java.simpleName
    }

}

