package me.skrilltrax.notes.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import me.skrilltrax.notes.helpers.GoogleSignInHelper
import me.skrilltrax.notes.R
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SplashActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account == null) {
            val googleSignInClient =
                requireNotNull(GoogleSignInHelper.getSignInClient(this@SplashActivity))
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
        else {
            GoogleSignInHelper.firebaseAuthWithGoogle(account)
            GoogleSignInHelper.driveSignIn(this, account)
            startActivity<MainActivity>()
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) {
                GoogleSignInHelper.parseIntent(this, data)
                startActivity<MainActivity>()
                finish()
            } else {
                toast("Auth canceled")
            }
        }
    }

    companion object {
        const val RC_SIGN_IN = 100
        const val TAG = "Splash Activity"
    }
}