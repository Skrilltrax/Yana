package me.skrilltrax.notes

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.google.android.material.button.MaterialButton

class NoteActivity : AppCompatActivity(), TextWatcher {

    lateinit var saveButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Utils.getTheme(this))
        setContentView(R.layout.note_layout)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)


        val editTitle: EditText = findViewById(R.id.edit_title)
        val editDetail: EditText = findViewById(R.id.edit_detail)
        saveButton = findViewById(R.id.fab)
        saveButton.isEnabled = false
        var position: Int = -1
        val bundle = intent.extras

        editTitle.setHintTextColor(Color.parseColor(Utils.getTransparentColor(this)))

        if (bundle != null) {
            val titleText: String? = bundle.getString("title")
            val detailText: String? = bundle.getString("detail")
            editTitle.setText(titleText)
            editDetail.setText(detailText)
            position = bundle.getInt("position")
        }
        var list: ArrayList<NoteData>?


        editTitle.addTextChangedListener(this)

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
                list?.add(note)
            }
            Log.e("NoteActivity",list.toString())
            Utils.saveNotes(this,list)
            RecyclerViewStuff.adapter.updateDataSet(this)

            finish()
        }
    }

    override fun afterTextChanged(s: Editable?) {
        saveButton.isEnabled = !s.isNullOrBlank()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        saveButton.isEnabled = !s.isNullOrBlank()
    }


    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        saveButton.isEnabled = !s.isNullOrBlank()
    }
}