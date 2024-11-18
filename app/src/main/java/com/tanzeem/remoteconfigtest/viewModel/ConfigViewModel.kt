package com.tanzeem.remoteconfigtest.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.tanzeem.remoteconfigtest.data.repository.RemoteConfigRepo
import com.tanzeem.remoteconfigtest.data.repository.RemoteConfigRepoImpl
import com.tanzeem.remoteconfigtest.util.SingleLiveEvent


class ConfigViewModel(
    private val remoteConfigRepo: RemoteConfigRepo = RemoteConfigRepoImpl()
) : ViewModel(), RemoteConfigRepoImpl.OnEventListener {

    val enableBuyerCreateContractLiveData by lazy { SingleLiveEvent<Boolean>() }

    init {
        (remoteConfigRepo as RemoteConfigRepoImpl).setOnEventListener(this)
    }


    override fun onEvent(er: Boolean?) {
        enableBuyerCreateContractLiveData.value = er
        Log.e("asasassasasasasasasassasa", "ConfigViewModel" + er)
    }


}
