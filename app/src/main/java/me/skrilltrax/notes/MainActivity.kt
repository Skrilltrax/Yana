package me.skrilltrax.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity","inOnCreate")
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        this.setSupportActionBar(toolbar)
        RecyclerViewStuff(this)

        val newNoteButton: MaterialButton = findViewById(R.id.fab)
        newNoteButton.setOnClickListener {

            val intent = Intent(this,NoteActivity::class.java)
            startActivity(intent)
        }
    }

}
