package com.tanzeem.remoteconfigtest.data.repository

import com.google.firebase.BuildConfig
import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.google.firebase.remoteconfig.remoteConfig
import com.tanzeem.remoteconfigtest.R
import com.tanzeem.remoteconfigtest.data.model.RemoteConfigs
import com.tanzeem.remoteconfigtest.util.SingleLiveEvent

class RemoteConfigRepoImpl(
    private val firebaseRemoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig,
    private val remoteConfigDefaultsResId: Int = R.xml.remote_config_defaults,
) : RemoteConfigRepo {

    override val valueLiveData by lazy { SingleLiveEvent<RemoteConfigs>() }

    override fun initConfigs(key: String) {

        if (BuildConfig.DEBUG) {
            val configSettings = com.google.firebase.remoteconfig.ktx.remoteConfigSettings {
                minimumFetchIntervalInSeconds = 1
            }
            firebaseRemoteConfig.setConfigSettingsAsync(configSettings)
        }

        firebaseRemoteConfig.setDefaultsAsync(remoteConfigDefaultsResId)
        fetchRemoteConfig(firebaseRemoteConfig)
        syncConfigurationsUpdates(firebaseRemoteConfig, key)

        if (valueLiveData.value != getConfigs(key))
            valueLiveData.value = getConfigs(key)
    }

    private fun fetchRemoteConfig(remoteConfig: FirebaseRemoteConfig) {
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener {
            }
    }

    private fun syncConfigurationsUpdates(remoteConfig: FirebaseRemoteConfig, key: String) {
        remoteConfig.addOnConfigUpdateListener(object : ConfigUpdateListener {
            override fun onUpdate(configUpdate: ConfigUpdate) {
                val updateKeysSize = configUpdate.updatedKeys.size
                remoteConfig.activate().addOnCompleteListener {
                    if (valueLiveData.value != getConfigs(key))
                        valueLiveData.value = getConfigs(key)
                }
            }

            override fun onError(error: FirebaseRemoteConfigException) {
            }
        })
    }

    override fun getConfigs(key: String): RemoteConfigs {
        return RemoteConfigs(
            string = firebaseRemoteConfig.getString(key),
            boolean = firebaseRemoteConfig.getBoolean(key)
        )
    }

}