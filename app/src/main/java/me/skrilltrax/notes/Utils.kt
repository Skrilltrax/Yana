package me.skrilltrax.notes

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken





class Utils {
    companion object {
        private const val PREFS_FILE_NAME: String = "MyPrefs"
        private const val PREF_NOTE: String = "PrefNote"

        fun saveNotes(context: Context, list: ArrayList<NoteData>?) {

            val spf: SharedPreferences = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
            val spfEditor: SharedPreferences.Editor = spf.edit()
            val gson = Gson()
            val json = gson.toJson(list)
            spfEditor.putString(PREF_NOTE,json).apply()

        }

        fun getNotes(context: Context) : ArrayList<NoteData>? {

            val list: ArrayList<NoteData>?
            val spf: SharedPreferences = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
            val gson = Gson()
            val json = spf.getString(PREF_NOTE, null)
            val type = object : TypeToken<List<NoteData>>() {
            }.type
            list = gson.fromJson(json, type)
            return list
        }

    }
}