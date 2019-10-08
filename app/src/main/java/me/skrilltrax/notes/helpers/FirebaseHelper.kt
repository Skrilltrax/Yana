package me.skrilltrax.notes.helpers

import android.util.Log
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object FirebaseHelper {

    var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var user: FirebaseUser

    fun signIn(credential: AuthCredential) {
        auth.signInWithCredential(credential).addOnSuccessListener {
            user = requireNotNull(auth.currentUser)
            Log.d("FirebaseHelper", user.displayName + " AAA ")
        }
    }

    fun isUserInitialized(): Boolean {
        return FirebaseHelper::user.isInitialized
    }
}