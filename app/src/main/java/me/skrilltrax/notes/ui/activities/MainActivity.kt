package me.skrilltrax.notes.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import me.skrilltrax.notes.*
import me.skrilltrax.notes.databinding.ActivityMainBinding
import me.skrilltrax.notes.ui.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by lazy { ViewModelProvider(this).get(MainActivityViewModel::class.java) }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        setObservers()
        setListeners()
    }

    private fun setListeners() {
        binding.fab.setOnClickListener {

        }
    }

    private fun setObservers() {

    }
}

