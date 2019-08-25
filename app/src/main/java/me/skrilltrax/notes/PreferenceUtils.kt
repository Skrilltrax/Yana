package me.skrilltrax.notes

import android.content.SharedPreferences
import androidx.preference.PreferenceManager

object PreferenceUtils {

    private val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.instance)
    private const val PREF_NOTE: String = "PrefNote"
    private const val THEME: String = "theme"
    private const val FIRST_RUN: String = "first_run"
    private const val USER_NAME: String = "user_name"

    fun getUserName(): String {
        return requireNotNull(prefs.getString(USER_NAME, "User") ?: "User")
    }

    fun getFirstRun(): Boolean {
        return requireNotNull(prefs.getBoolean(FIRST_RUN, true))
    }

/*    fun getTheme(): Int {
        return requireNotNull(prefs.getInt(THEME, R.style.LightTheme))
    }*/

    fun changeTheme(theme: Int) {
        prefs.edit().putInt(THEME, theme).apply()
    }
}