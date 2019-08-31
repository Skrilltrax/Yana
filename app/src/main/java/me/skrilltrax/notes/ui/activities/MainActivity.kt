package me.skrilltrax.notes.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.services.drive.Drive
import kotlinx.coroutines.*
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
//            CoroutineScope(Dispatchers.IO).launch {
//                DriveService.createFile()
//            }
            CoroutineScope(Dispatchers.IO).launch {
                val files = DriveService.getFiles()
//                val filesNum = DriveService.getFiles()
                withContext(Dispatchers.Main) {
                    if (files != null) {
                        for (i in 0 until files.size) {
                            Log.d("MainActivity", files[i].name)
                        }
                    }
//                    Log.d("MainActivity", "$filesNum AAA ")
                }
            }
        }
    }

    private fun setObservers() {

    }
}

