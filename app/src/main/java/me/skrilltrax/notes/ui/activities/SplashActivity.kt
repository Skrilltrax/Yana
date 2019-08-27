package me.skrilltrax.notes.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import me.skrilltrax.notes.R
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SplashActivity: AppCompatActivity() {

    val providers = arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startActivityForResult(AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setTheme(R.style.AppTheme)
            .build(), RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                startActivity<MainActivity>()
            } else {
                if (response == null) {
                } else {
                    toast(response.error?.message!!)
                }
            }
        }
    }

    companion object {
        const val RC_SIGN_IN = 100
    }
}