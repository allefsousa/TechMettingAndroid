package com.allef.developer.marketplaceoffers

import android.app.Application
import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.gms.analytics.Tracker
import com.google.firebase.database.FirebaseDatabase


class App: Application() {

    private lateinit var sAnalytics: GoogleAnalytics
    private var sTracker: Tracker? = null


    override fun onCreate() {
        super.onCreate()
        sAnalytics = GoogleAnalytics.getInstance(this)
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)

    }
    fun getDefaultTracker(): Tracker? {
        synchronized(this) {
            if (sTracker == null) {
                sTracker = sAnalytics.newTracker(R.xml.global_tracker)
            }
            return sTracker

        }

    }
}