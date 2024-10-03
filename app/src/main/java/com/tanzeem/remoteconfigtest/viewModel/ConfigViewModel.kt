package com.tanzeem.remoteconfigtest.viewModel

import androidx.lifecycle.ViewModel
import com.tanzeem.remoteconfigtest.data.repository.RemoteConfigRepo
import com.tanzeem.remoteconfigtest.data.repository.RemoteConfigRepoImpl

class ConfigViewModel(
    val remoteConfigRepo: RemoteConfigRepo = RemoteConfigRepoImpl()
) : ViewModel() {

}