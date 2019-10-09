package me.skrilltrax.notes.helpers

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.api.services.drive.Drive
import com.google.firebase.auth.FirebaseUser

object AccountHelper {

    var googleAccount : GoogleSignInAccount? = null
    var firebaseUser : FirebaseUser? = null
    var driveClient : Drive? = null
    private const val RC_SIGN_IN = 100

    fun signIn(activity: AppCompatActivity) {
        googleAccount = GoogleSignIn.getLastSignedInAccount(activity)
        if (googleAccount == null) {
            val googleSignInClient =
                requireNotNull(GoogleSignInHelper.getSignInClient(activity))
            val signInIntent = googleSignInClient.signInIntent
            activity.startActivityForResult(signInIntent, RC_SIGN_IN)
        }
        else {
            firebaseUser = GoogleSignInHelper.firebaseAuthWithGoogle(requireNotNull(googleAccount))
            driveClient = GoogleSignInHelper.driveSignIn(activity, requireNotNull(googleAccount))
        }
    }

}
