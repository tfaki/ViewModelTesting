package com.android.viewmodeltesting

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ViewModelTestingApp : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: ViewModelTestingApp? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        applicationContext()
    }
}