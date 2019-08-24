package me.skrilltrax.notes.ui.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.preference.PreferenceManager
import com.google.android.material.button.MaterialButton
import me.skrilltrax.notes.*

class MainActivity : AppCompatActivity() {
    private lateinit var userText: TextView

    lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Utils.getTheme(this))
//        setContentView(R.layout.activity_main)

        userText = findViewById(R.id.title_bottom)
        prefs = PreferenceManager.getDefaultSharedPreferences(this)
        userText.text = prefs.getString("user_name","User")
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        this.setSupportActionBar(toolbar)
        RecyclerViewStuff(this)

        val settingsButton: ImageButton = findViewById(R.id.settings_button)
        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        val newNoteButton: MaterialButton = findViewById(R.id.fab)
        newNoteButton.setOnClickListener {
            val intent = Intent(this, NoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        userText.text = prefs.getString("user_name","User")
    }
}
