package me.skrilltrax.notes.helpers

import android.util.Log
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth

object FirebaseHelper {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signIn(credential: AuthCredential) {
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                AccountHelper.firebaseUser = requireNotNull(auth.currentUser)
            } else {
                Log.d("FirebaseHelper", "ERROR")
            }
//            Log.d("FirebaseHelperComplete", user?.displayName + " AAA ")
        }
//        if (user != null) {
//            return user as FirebaseUser
//        } else {
//            throw AccountAccessException("Error Initializing Firebase User")
//        }
    }
}
