package me.skrilltrax.notes.utils

enum class NoteType constructor(private val noteId: Int) {
    NOTE(1),
    REMINDER(2);

    companion object {

        fun getNoteByType(id: Int): NoteType? {
            val noteArray = values()
            for (i in 0 until noteArray.size) {
                if (id == noteArray[i].noteId) {
                    return noteArray[i]
                }
            }
            return null
        }
    }
}
