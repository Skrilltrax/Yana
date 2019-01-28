package me.skrilltrax.notes

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class NoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.note_layout)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)


        val editTitle: EditText = findViewById(R.id.edit_title)
        val editDetail: EditText = findViewById(R.id.edit_detail)
        val saveButton: MaterialButton = findViewById(R.id.fab)
        var position: Int = -1
        val bundle = intent.extras

        if (bundle != null) {
            val titleText: String? = bundle.getString("title")
            val detailText: String? = bundle.getString("detail")
            editTitle.setText(titleText)
            editDetail.setText(detailText)
            position = bundle.getInt("position")
        }
        var list: ArrayList<NoteData>?

        saveButton.setOnClickListener {


            list = Utils.getNotes(this)
            val note = NoteData()
            note.detailText = editDetail.text.toString()
            note.titleText = editTitle.text.toString()
            Log.e("NoteActivity", "${note.detailText} + ${note.titleText}")
            if (list.isNullOrEmpty()) {
                list = ArrayList()
            }
            if (position != -1) {
                list?.get(position)?.titleText = note.titleText
                list?.get(position)?.detailText = note.detailText
            } else {
                list!!.add(note)
            }
            Log.e("NoteActivity",list.toString())
            Utils.saveNotes(this,list)
            RecyclerViewStuff.adapter.updateDataSet(this)
            finish()
        }
    }
}