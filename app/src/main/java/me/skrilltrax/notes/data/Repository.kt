package me.skrilltrax.notes.data

import android.util.Log
import io.realm.Realm
import me.skrilltrax.notes.data.model.NoteData

class Repository(private val realm: Realm) {

    fun getAllNotes(): List<NoteData> {
        return realm.where(NoteData::class.java).findAll()
    }

    fun addNotes(note: NoteData) {
        realm.beginTransaction()
        val notesList = realm.where(NoteData::class.java).findAll()
        var id = notesList.size
        note.id = ++id
        realm.insertOrUpdate(note)
        realm.commitTransaction()
        Log.d("id", id.toString())
        Log.d("title", note.titleText)
        Log.d("title", note.detailText)
    }
}
