package com.tanzeem.remoteconfigtest.data.repository


interface RemoteConfigRepo {
    fun initConfigs()
    fun getString(key: String): String
    fun getInt(key: String): Int
    fun getDouble(key: String): Double
    fun getFloat(key: String): Float
    fun getLong(key: String): Long
    fun getBoolean(key: String): Boolean
    fun getJson(key: String): String
    fun release()
}