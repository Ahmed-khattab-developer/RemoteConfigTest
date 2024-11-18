package com.tanzeem.remoteconfigtest.data.repository

import android.util.Log
import com.google.firebase.BuildConfig
import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.google.firebase.remoteconfig.remoteConfig
import com.tanzeem.remoteconfigtest.R

class RemoteConfigRepoImpl(
    private val firebaseRemoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig,
    private val remoteConfigDefaultsResId: Int = R.xml.remote_config_defaults,
) : RemoteConfigRepo {

    private var mOnEventListener: OnEventListener? = null

    fun setOnEventListener(listener: OnEventListener?) {
        mOnEventListener = listener
    }

    override fun initConfigs() {
        if (BuildConfig.DEBUG) {
            val configSettings = com.google.firebase.remoteconfig.ktx.remoteConfigSettings {
                minimumFetchIntervalInSeconds = 1
            }
            firebaseRemoteConfig.setConfigSettingsAsync(configSettings)
        }

        firebaseRemoteConfig.setDefaultsAsync(remoteConfigDefaultsResId)
        fetchRemoteConfig(firebaseRemoteConfig)
        syncConfigurationsUpdates(firebaseRemoteConfig)


    }

    private fun fetchRemoteConfig(remoteConfig: FirebaseRemoteConfig) {
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener {

                    mOnEventListener?.onEvent(firebaseRemoteConfig.getBoolean("enable_button"))

                Log.e(
                    "asasassasasasasasasassasa",
                    "fetchRemoteConfig" + firebaseRemoteConfig.getBoolean("enable_button")
                )
            }
    }

    private fun syncConfigurationsUpdates(remoteConfig: FirebaseRemoteConfig) {
        remoteConfig.addOnConfigUpdateListener(object : ConfigUpdateListener {
            override fun onUpdate(configUpdate: ConfigUpdate) {
                val updateKeysSize = configUpdate.updatedKeys.size
                remoteConfig.activate().addOnCompleteListener {
                    mOnEventListener?.onEvent(firebaseRemoteConfig.getBoolean("enable_button"))
                    Log.e(
                        "asasassasasasasasasassasa",
                        "syncConfigurationsUpdates" + firebaseRemoteConfig.getBoolean("enable_button")
                    )

                }
            }

            override fun onError(error: FirebaseRemoteConfigException) {}
        })
    }

    override fun getString(key: String): String {
        return firebaseRemoteConfig.getString(key)
    }

    override fun getInt(key: String): Int {
        return firebaseRemoteConfig.getString(key).toIntOrNull() ?: -1
    }

    override fun getDouble(key: String): Double {
        return firebaseRemoteConfig.getDouble(key)
    }

    override fun getFloat(key: String): Float {
        return firebaseRemoteConfig.getString(key).toFloatOrNull() ?: -1f
    }

    override fun getLong(key: String): Long {
        return firebaseRemoteConfig.getLong(key)
    }

    override fun getBoolean(key: String): Boolean {
        return firebaseRemoteConfig.getBoolean(key)
    }

    override fun getJson(key: String): String {
        return firebaseRemoteConfig.getString(key)
    }

    override fun release() {
        firebaseRemoteConfig.reset()
            .addOnSuccessListener { }
            .addOnFailureListener { it.printStackTrace() }
    }

    interface OnEventListener {
        fun onEvent(er: Boolean?) // or void onEvent(); as per your need
    }

}