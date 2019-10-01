package me.skrilltrax.notes

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import me.skrilltrax.notes.model.NoteData


class Utils {
    companion object {
        private const val PREFS_FILE_NAME: String = "MyPrefs"
        private const val PREF_NOTE: String = "PrefNote"
        private const val THEME: String = "Theme"
        private const val FIRST_RUN: String = "First Run"
        private const val USER_NAME: String = "user_name"

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

        fun getTransparentColor(context: Context) : String {
            var color:String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Integer.toHexString(context.getColor(R.color.secondary_color))
            } else {
                //noinspection deprecation
                Integer.toHexString(context.resources.getColor(R.color.secondary_color))
            }

            color = "#" + color.replaceRange(0,2,"70")
            return color
        }
/*
        fun getTheme(context: Context?) : Int {
            val sharedPreferences = context?.getSharedPreferences(PREFS_FILE_NAME,Context.MODE_PRIVATE)
            return sharedPreferences?.getInt(THEME,R.style.LightTheme) ?: R.style.LightTheme
        }

        fun changeTheme(context: Context?, theme: Int) {
            val sharedPreferences = context?.getSharedPreferences(PREFS_FILE_NAME,Context.MODE_PRIVATE)
            sharedPreferences?.edit()?.putInt(THEME, theme)?.apply()
        }
 */

        fun isFirstRun(context: Context): Boolean {
            val sharedPreferences = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getBoolean(FIRST_RUN,true)
        }

        fun getUserName(context: Context): String? {
            val sharedPreferences = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getString(USER_NAME,"User")
        }

        fun showShortAnchoredSnackbar(view: View, anchorViewId: Int, stringRes: Int) {
            Snackbar.make(view, stringRes, Snackbar.LENGTH_SHORT)
                .setAnchorView(anchorViewId)
                .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
                .show()
        }

    }
}