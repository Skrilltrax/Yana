package me.skrilltrax.notes.utils

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import com.google.android.material.snackbar.Snackbar
import me.skrilltrax.notes.MyApplication

object PreferenceUtils {

    private val prefs: SharedPreferences = MyApplication.instance.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
    private const val THEME: String = "theme"
    private const val FIRST_RUN: String = "first_run"
    private const val USER_NAME: String = "user_name"

    fun getUserName(): String {
        return requireNotNull(
            prefs.getString(
                USER_NAME, "User") ?: "User")
    }

    fun getFirstRun(): Boolean {
        return requireNotNull(
            prefs.getBoolean(
                FIRST_RUN, true))
    }

/*    fun getTheme(): Int {
        return requireNotNull(prefs.getInt(THEME, R.style.LightTheme))
    }*/

    fun changeTheme(theme: Int) {
        prefs.edit().putInt(
            THEME, theme).apply()
    }

    fun showShortAnchoredSnackbar(view: View, anchorViewId: Int, stringRes: Int) {
        Snackbar.make(view, stringRes, Snackbar.LENGTH_SHORT)
            .setAnchorView(anchorViewId)
            .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
            .show()
    }
}
