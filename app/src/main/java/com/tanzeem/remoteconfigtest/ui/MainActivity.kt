package com.tanzeem.remoteconfigtest.ui

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.tanzeem.remoteconfigtest.R
import com.tanzeem.remoteconfigtest.data.model.RemoteConfigs
import com.tanzeem.remoteconfigtest.viewModel.ConfigViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<ConfigViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.enableBuyerCreateContractLiveData.observe(this) { updateConfigs(it) }
    }

    private fun updateConfigs(boolean: Boolean) {

        findViewById<Button>(R.id.hide_btn).isVisible = boolean
        Toast.makeText(
            this, "button visibility is $boolean", Toast.LENGTH_SHORT
        ).show()

    }


}