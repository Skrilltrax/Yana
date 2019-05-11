package me.skrilltrax.notes.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import me.skrilltrax.notes.Application.Companion.getApplicationContext
import me.skrilltrax.notes.R
import me.skrilltrax.notes.model.NoteData


class Utils {
    companion object {
        private const val PREFS_FILE_NAME: String = "MyPrefs"
        private const val PREF_NOTE: String = "PrefNote"
        private const val THEME: String = "Theme"
        private const val FIRST_RUN: String = "First Run"
        private const val USER_NAME: String = "user_name"

        private val sharedPreferences: SharedPreferences =
            getApplicationContext().getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
        private val sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()
        private val gson = Gson()

        fun saveNotes(list: ArrayList<NoteData>?) {
            val json = gson.toJson(list)
            sharedPreferencesEditor.putString(PREF_NOTE, json).apply()

        }

        fun getNotes(): ArrayList<NoteData> {

            var list = ArrayList<NoteData>()
            val json = sharedPreferences.getString(PREF_NOTE, null)
            val type = object : TypeToken<List<NoteData>>() {
            }.type
            if (json != null) {
                list = gson.fromJson(json, type)
            }
            return list
        }

        fun getTransparentColor(): String {
            var color: String =
                Integer.toHexString(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent))
            color = "#" + color.replaceRange(0, 2, "70")
            return color
        }

        fun getTheme(): Int {
            val sharedPreferences = getApplicationContext().getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
            return sharedPreferences?.getInt(
                THEME,
                R.style.LightTheme
            ) ?: R.style.LightTheme
        }

        fun changeTheme(theme: Int) {
            val sharedPreferences = getApplicationContext().getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
            sharedPreferences?.edit()?.putInt(THEME, theme)?.apply()
        }

        fun isFirstRun(): Boolean {
            val sharedPreferences = getApplicationContext().getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getBoolean(FIRST_RUN, true)
        }

        fun getUserName(): String {
            val sharedPreferences = getApplicationContext().getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getString(USER_NAME, "User") as String
        }

    }
}