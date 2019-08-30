package me.skrilltrax.notes.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.FileContent
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.services.drive.Drive
import com.google.api.services.drive.model.File
import me.skrilltrax.notes.*
import me.skrilltrax.notes.DriveQuickStart.APPLICATION_NAME
import me.skrilltrax.notes.DriveQuickStart.JSON_FACTORY
import me.skrilltrax.notes.DriveQuickStart.getCredentials
import me.skrilltrax.notes.databinding.ActivityMainBinding
import me.skrilltrax.notes.ui.viewmodel.MainActivityViewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by lazy { ViewModelProvider(this).get(MainActivityViewModel::class.java) }
    private lateinit var binding: ActivityMainBinding
    private lateinit var HTTP_TRANSPORT: NetHttpTransport
    private lateinit var service: Drive

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        initDrive()
        setObservers()
        setListeners()
    }

    private fun initDrive() {
        HTTP_TRANSPORT = NetHttpTransport()
        service = Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
            .setApplicationName(APPLICATION_NAME)
            .build();
    }

    private fun setListeners() {
        binding.fab.setOnClickListener {
            val fileMetadata: File = File()
            fileMetadata.name = "config.json";
            fileMetadata.parents = Collections.singletonList("appDataFolder");
            val filePath: java.io.File  = java.io.File("files/config.json");
            val mediaContent: FileContent = FileContent("application/json", filePath);
            val file: File = service.files().create(fileMetadata, mediaContent)
                .setFields("id")
                .execute();
            Log.d("OnClick", "File ID: " + file.id);
        }
    }

    private fun setObservers() {

    }
}

