package me.skrilltrax.notes

import android.content.Context
import java.lang.ref.WeakReference

class Application: android.app.Application() {

    init {
        weakRef = WeakReference(this)
        weakContext = WeakReference(this.applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        private lateinit var weakRef: WeakReference<Application>
        private lateinit var weakContext: WeakReference<Context>

        fun get(): Application {
            return weakRef.get() as Application
        }

        fun getApplicationContext() : Context{
            return weakContext.get() as Context
        }
    }
}