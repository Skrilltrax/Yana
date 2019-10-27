package me.skrilltrax.notes.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.Date
import me.skrilltrax.notes.NoteType

open class NoteData @JvmOverloads constructor(
  @PrimaryKey
  var id: Int? = null,
  var titleText: String = "",
  var detailText: String = "",
  var noteTypeInt: Int = -1,
  var startDate: Date = Date(),
  var endDate: Date = Date()
) : RealmObject() {
    val noteType: NoteType
        get() = requireNotNull(NoteType.getNoteByType(noteTypeInt))
}
