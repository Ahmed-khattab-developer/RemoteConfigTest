package com.tanzeem.remoteconfigtest.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.tanzeem.remoteconfigtest.data.repository.RemoteConfigRepo
import com.tanzeem.remoteconfigtest.data.repository.RemoteConfigRepoImpl
import com.tanzeem.remoteconfigtest.util.SingleLiveEvent


class ConfigViewModel(

) : ViewModel() {

    val remoteConfigRepo: RemoteConfigRepo = RemoteConfigRepoImpl(::onEvent)
    val enableBuyerCreateContractLiveData by lazy { SingleLiveEvent<Boolean>() }


     private fun onEvent(ss: Boolean?) {
        enableBuyerCreateContractLiveData.value = ss
        Log.e("asasassasasasasasasassasa", "ConfigViewModel" + ss)
    }


}
