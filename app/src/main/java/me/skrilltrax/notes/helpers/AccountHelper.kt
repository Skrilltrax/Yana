package me.skrilltrax.notes.helpers

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.api.services.drive.Drive
import com.google.firebase.auth.FirebaseUser
import me.skrilltrax.notes.AccountAccessException

object AccountHelper {

    var googleAccount: GoogleSignInAccount? = null
    var firebaseUser: FirebaseUser? = null
    var driveClient: Drive? = null

    private const val RC_SIGN_IN = 100
    private var isSignedIn = false

    fun signIn(activity: AppCompatActivity): Boolean {
        googleAccount = GoogleSignIn.getLastSignedInAccount(activity)
        when {
            googleAccount == null -> signInWithoutAccount(activity)
            googleAccount?.isExpired == true -> signInWithoutAccount(activity).also { Log.d("AccountHelper", "Expired") }
            else -> signInWithAccount(activity)
        }
        return isSignedIn
    }

    private fun signInWithoutAccount(activity: AppCompatActivity) {
        Log.d("AccountHelper", "signInWithoutAccount")
        val googleSignInClient = requireNotNull(GoogleSignInHelper.getSignInClient(activity))
        val signInIntent = googleSignInClient.signInIntent
        activity.startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun signInWithAccount(activity: AppCompatActivity) {
        Log.d("AccountHelper", "signInWithAccount")
        isSignedIn = true
        GoogleSignInHelper.firebaseAuthWithGoogle(requireNotNull(googleAccount))
        GoogleSignInHelper.driveSignIn(activity.applicationContext, requireNotNull(googleAccount))
    }

    private fun validateOrThrowException(field: Any?, reason: String): Any {
        if (field == null) {
            throw AccountAccessException(reason)
        } else {
            return field
        }
    }
}
