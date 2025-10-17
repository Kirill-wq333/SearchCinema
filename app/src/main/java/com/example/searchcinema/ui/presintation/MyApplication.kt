package com.example.searchcinema.ui.presintation

import android.app.Application
import com.my.tracker.MyTracker
import dagger.hilt.android.HiltAndroidApp

private const val MYTRACKER_KEY = "23523622622626"

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MyTracker.initTracker(MYTRACKER_KEY, this)
        MyTracker.getTrackerConfig().bufferingPeriod = 30
    }
}
