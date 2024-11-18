package com.tanzeem.remoteconfigtest.ui

import android.app.Application
import android.content.Context
import com.tanzeem.remoteconfigtest.data.model.RemoteConfigConfigurator

lateinit var appContext: Context

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
        RemoteConfigConfigurator.setUp().init()
    }

}