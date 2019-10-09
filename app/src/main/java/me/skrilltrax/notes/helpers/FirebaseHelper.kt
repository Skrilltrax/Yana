package me.skrilltrax.notes.helpers

import android.util.Log
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import me.skrilltrax.notes.AccountAccessException

object FirebaseHelper {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private var user: FirebaseUser? = null

    @Throws(AccountAccessException::class)
    fun signIn(credential: AuthCredential) : FirebaseUser {
        Log.d("in SignIN", "here")
        auth.signInWithCredential(credential).addOnSuccessListener {
            user = requireNotNull(auth.currentUser)
            Log.d("FirebaseHelper", user?.displayName + " AAA ")
        }.addOnCompleteListener {
            if (it.isSuccessful) {
                user = requireNotNull(auth.currentUser)
            }
            Log.d("FirebaseHelperComplete", user?.displayName + " AAA ")
        }
        if (user != null) {
            return user as FirebaseUser
        } else {
            throw AccountAccessException("Error Initializing Firebase User")
        }
    }

/*    fun isUserInitialized(): Boolean {
        return FirebaseHelper::user.isInitialized
    }*/
}