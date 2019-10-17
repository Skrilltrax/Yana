package me.skrilltrax.notes.helpers

import android.util.Log
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.http.FileContent
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.drive.Drive
import com.google.api.services.drive.model.File
import com.google.api.services.drive.model.FileList
import kotlinx.coroutines.*
import me.skrilltrax.notes.MyApplication
import java.util.*

object DriveService {
    private lateinit var googleDriveService: Drive

    fun init(driveCredential: GoogleAccountCredential) {
        Log.d("DriveService", "init")
        googleDriveService =  Drive.Builder(
            AndroidHttp.newCompatibleTransport(),
            JacksonFactory.getDefaultInstance(),
            driveCredential
        ).setApplicationName("Notes").build()

        AccountHelper.driveClient = googleDriveService
    }

    private fun isDriveInitialized(): Boolean {
        return DriveService::googleDriveService.isInitialized
    }

    fun getUserIp(): String? {
        if (isDriveInitialized())
            return googleDriveService.about().get().userIp
        else
            throw UninitializedPropertyAccessException("googleDriveService not initialized")
    }

    suspend fun getFiles(): MutableList<File>? = coroutineScope {
        val files: FileList = googleDriveService.Files()
            .list()
            .setSpaces("appDataFolder")
            .setFields("nextPageToken, files(id, name)")
            .execute()
        return@coroutineScope files.files
    }

    suspend fun createFile(name: String) = coroutineScope {
        val fileMetadata = File()
        fileMetadata.name = name;
        fileMetadata.parents = Collections.singletonList("appDataFolder");
        val db = FileContent("application/txt", java.io.File(MyApplication.realm.path))
        val file = googleDriveService.files().create(fileMetadata).setFields("id").execute()
        Log.d("driveServiceCreateFile", file.id)
    }
}