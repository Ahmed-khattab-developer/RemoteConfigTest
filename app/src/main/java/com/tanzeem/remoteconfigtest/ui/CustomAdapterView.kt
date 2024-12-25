package com.tanzeem.remoteconfigtest.ui

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CustomAdapterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

    private lateinit var stepAdapter: StepAdapter

    init {
        val gridLayoutManager = GridLayoutManager(context, 4)
        gridLayoutManager.reverseLayout = true
        layoutManager = gridLayoutManager
    }

    fun setupAdapter(status: Status) {
        stepAdapter = StepAdapter(status)
        adapter = stepAdapter
    }

    fun updateState(status: Status) {
        stepAdapter.updateState(status)
    }
}