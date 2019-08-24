package me.skrilltrax.notes.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import me.skrilltrax.notes.NoteType
import java.util.*

open class NoteData (
    @PrimaryKey
    private var id: String? = null,
    var titleText: String,
    var detailText: String,
    var noteTypeInt: Int,
    var startDate: Date,
    var endDate: Date
) : RealmObject() {
    constructor(): this(null, "", "", -1, Date(), Date())
    val noteType: NoteType
        get() = requireNotNull(NoteType.getNoteByType(noteTypeInt))
}