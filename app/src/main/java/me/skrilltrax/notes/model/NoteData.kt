package me.skrilltrax.notes.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import me.skrilltrax.notes.NoteType
import java.util.*

class NoteData : RealmObject() {
    @PrimaryKey
    private var id: String? = null
    private lateinit var noteType: NoteType
    private lateinit var titleText: String
    private lateinit var detailText: String
    private lateinit var startDate: Date
    private lateinit var endDate: Date
}