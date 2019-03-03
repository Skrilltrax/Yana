package me.skrilltrax.notes

import android.content.Intent
import android.content.SharedPreferences
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.preference.PreferenceManager
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity(), LongClickListener{
    private lateinit var userText: TextView

    lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Utils.getTheme(this))
        setContentView(R.layout.activity_main)

        userText = findViewById(R.id.title_bottom)
        prefs = PreferenceManager.getDefaultSharedPreferences(this)
        userText.text = prefs.getString("user_name","User")
        Log.e("MainActivity",userText.text.toString())
        Log.d("MainActivity","inOnCreate")
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        this.setSupportActionBar(toolbar)
        RecyclerViewStuff(this,this)

        val settingsButton: ImageButton = findViewById(R.id.settings_button)
        settingsButton.setOnClickListener {
            val intent = Intent(this,SettingsActivity::class.java)
            startActivity(intent)
        }

        val newNoteButton: MaterialButton = findViewById(R.id.fab)
        newNoteButton.setOnClickListener {

            val intent = Intent(this,NoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        userText.text = prefs.getString("user_name","User")
    }
    override fun onLongClick(): Boolean {
        val modalSheet = ModalSheet()
        modalSheet.show(supportFragmentManager,modalSheet.tag)
        return true
    }


}
