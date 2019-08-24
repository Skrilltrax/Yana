package me.skrilltrax.notes.ui.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import com.google.android.material.button.MaterialButton
import me.skrilltrax.notes.*
import me.skrilltrax.notes.databinding.ActivityMainBinding
import me.skrilltrax.notes.ui.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by lazy { ViewModelProviders.of(this).get(MainActivityViewModel::class.java) }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Utils.getTheme(this))
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel

        setListeners()
    }

    private fun setListeners() {
        binding.settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        binding.fab.setOnClickListener {
//            val intent = Intent(this, NoteActivity::class.java)
//            startActivity(intent)
            viewModel.changeUserName("Adi")
        }
    }

    private fun setObservers() {
        viewModel.userName.observe(this, Observer {
            binding.titleBottom.text = it + "AAAA"
        })
    }
}

