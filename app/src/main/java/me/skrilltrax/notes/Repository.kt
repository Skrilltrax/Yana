package me.skrilltrax.notes

import io.realm.Realm
import me.skrilltrax.notes.model.NoteData

class Repository(private val realm: Realm) {

    fun getAllNotes(): List<NoteData> {
        return realm.where(NoteData::class.java).findAllAsync()
    }
}