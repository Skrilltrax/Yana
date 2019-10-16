package me.skrilltrax.notes.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import me.skrilltrax.notes.AccountAccessException
import me.skrilltrax.notes.helpers.GoogleSignInHelper
import me.skrilltrax.notes.R
import me.skrilltrax.notes.helpers.AccountHelper
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SplashActivity: BaseActivity() {

    var isSignedIn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        isSignedIn = AccountHelper.signIn(this)
        if (isSignedIn) {
            startActivity<MainActivity>()
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            if (resultCode != Activity.RESULT_OK) {
                toast("Auth canceled")
            }
            startActivity<MainActivity>()
            finish()
        }
    }

    companion object {
        const val RC_SIGN_IN = 100
        const val TAG = "Splash Activity"
    }
}