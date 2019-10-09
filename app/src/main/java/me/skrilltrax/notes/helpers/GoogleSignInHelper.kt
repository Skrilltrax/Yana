package me.skrilltrax.notes.helpers

import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.services.drive.Drive
import com.google.api.services.drive.DriveScopes
import com.google.firebase.auth.*
import me.skrilltrax.notes.AccountAccessException
import me.skrilltrax.notes.R
import me.skrilltrax.notes.ui.activities.SplashActivity
import java.util.*

object GoogleSignInHelper {

    private lateinit var credential: AuthCredential
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var driveClient: Drive

    fun getSignInClient(context: Context): GoogleSignInClient? {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .requestScopes(Scope(DriveScopes.DRIVE_APPDATA), Scope(DriveScopes.DRIVE_FILE))
            .build()

        return GoogleSignIn.getClient(context, gso)
    }

    fun parseIntent(context: Context, data: Intent?) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        task.addOnSuccessListener {
            Log.d("GoogleSignInHelper", "Google Sign In Success")
            try {
                driveClient = driveSignIn(context, it)
                firebaseUser = firebaseAuthWithGoogle(it)
            } catch (e: ApiException) {
                Log.w(SplashActivity.TAG, "Google sign in failed", e)
            } catch (e: IllegalAccessException) {
                Log.w("Splash", "User not logged in")
            }
        }
    }

    @Throws(AccountAccessException::class)
    fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) : FirebaseUser {
        Log.d("SignInHelper", "firebaseAuthWithGoogle")
        credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseUser = FirebaseHelper.signIn(credential)
        return firebaseUser
    }

    fun driveSignIn(context: Context, acct: GoogleSignInAccount) : Drive {
        Log.d("SignInHelper", "driveSignIn")
        val driveCredential = GoogleAccountCredential.usingOAuth2(
            context.applicationContext, Collections.singleton(DriveScopes.DRIVE_FILE)
        )
        Log.d("driveSignIn", acct.displayName + " AAAAA")
        driveCredential.selectedAccount = acct.account
        driveClient = DriveService.init(driveCredential)
        return driveClient
    }
}


