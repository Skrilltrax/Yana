package me.skrilltrax.notes.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.SignInButton
import me.skrilltrax.notes.GoogleSignInHelper
import me.skrilltrax.notes.R
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.mortbay.jetty.Main

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val signInButton: SignInButton = findViewById(R.id.sign_in_button)

        signInButton.setOnClickListener {
            val googleSignInClient = requireNotNull(GoogleSignInHelper.getSignInClient(this@SplashActivity))
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == Activity.RESULT_OK) {
                GoogleSignInHelper.parseIntent(this, data)
                startActivity<MainActivity>()
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