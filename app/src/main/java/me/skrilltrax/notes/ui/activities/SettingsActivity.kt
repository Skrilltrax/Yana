package me.skrilltrax.notes.ui.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.transaction
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat
import me.skrilltrax.notes.R
import me.skrilltrax.notes.Utils

class SettingsActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Utils.getTheme(this))
        if(supportFragmentManager.findFragmentById(R.id.settings_fragment) == null) {
            supportFragmentManager.transaction {
                add(
                    R.id.settings_fragment,
                    SettingsFragment()
                )
            }
        }



        setContentView(R.layout.settings_activity)

        val toolbar: Toolbar = findViewById(R.id.toolbar_settings)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

        var darkTheme: Boolean? = null
        private var etp: EditTextPreference? = null

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.preferences, rootKey)
            etp =  findPreference("user_name") as androidx.preference.EditTextPreference
            etp?.summary = preferenceManager.sharedPreferences.getString("user_name","User")
        }

        override fun onResume() {
            super.onResume()
            preferenceManager.sharedPreferences.registerOnSharedPreferenceChangeListener(this)

        }

        override fun onPause() {
            preferenceManager.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
            super.onPause()
        }

        override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
            Log.e("Theme","WOOOORK")

            if(key == "theme_switch") {
                darkTheme = sharedPreferences?.getBoolean(key,false)
                if (darkTheme!!) {
                    Utils.changeTheme(
                        context,
                        R.style.DarkTheme
                    )
                    val intent = Intent(activity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    activity?.finish()
                } else {
                    Utils.changeTheme(
                        context,
                        R.style.LightTheme
                    )
                    val intent = Intent(activity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    activity?.finish()
                }
            }

            if(key == "user_name") {
                Log.e("PREFSCHANGES",key)
                etp?.summary = sharedPreferences?.getString(key,"User")
            }
        }

    }

    companion object {
        private const val THEMESWITCH: String = "theme_switch"
    }
}