package com.tanzeem.remoteconfigtest.ui

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AdapterView @JvmOverloads constructor(
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

    fun setupAdapter(
        context: Context,
        contractStatus: ContractStatus,
        escalationDelivery: EscalationTypeEnum? = null,
        escalationItem: EscalationTypeEnum? = null
    ) {
        stepAdapter = StepAdapter(context, contractStatus, escalationDelivery, escalationItem)
        adapter = stepAdapter
    }

    fun updateState(
        contractStatus: ContractStatus,
        escalationDelivery: EscalationTypeEnum? = null,
        escalationItem: EscalationTypeEnum? = null
    ) {
        stepAdapter.updateState(contractStatus, escalationDelivery, escalationItem)
    }
}