package me.skrilltrax.notes

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
        realm = Realm.getInstance(realmConfig)
    }

    companion object {
        lateinit var instance: MyApplication
        lateinit var realm: Realm
    }
}
