package com.tanzeem.remoteconfigtest.data.model

import com.tanzeem.remoteconfigtest.data.repository.RemoteConfigRepo
import com.tanzeem.remoteconfigtest.data.repository.RemoteConfigRepoImpl

object RemoteConfigConfigurator {
    private var configurations: RemoteConfigRepo? = null

    fun setUp(configurations: RemoteConfigRepo = RemoteConfigRepoImpl()): RemoteConfigConfigurator {
        RemoteConfigConfigurator.configurations = configurations
        return this
    }

    fun init() {
        configurations?.initConfigs()
    }
}