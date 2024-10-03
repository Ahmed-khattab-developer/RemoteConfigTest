package com.tanzeem.remoteconfigtest.data.repository

import com.tanzeem.remoteconfigtest.data.model.RemoteConfigs
import com.tanzeem.remoteconfigtest.util.SingleLiveEvent

interface RemoteConfigRepo {
    val valueLiveData: SingleLiveEvent<RemoteConfigs>
    fun initConfigs(key: String = "enable_button")
    fun getConfigs(key:String): RemoteConfigs
}