package me.skrilltrax.notes.ui.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import me.skrilltrax.notes.helpers.GoogleSignInHelper
import org.jetbrains.anko.toast

open class BaseActivity : AppCompatActivity() {

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SplashActivity.RC_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) {
                GoogleSignInHelper.parseIntent(this, data)
            } else {
                toast("Auth canceled")
            }
        }
    }
}
