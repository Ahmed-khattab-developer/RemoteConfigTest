package com.tanzeem.remoteconfigtest.ui

import android.os.Bundle
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tanzeem.remoteconfigtest.R

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.step_recycler_view)


        val gridLayoutManager = GridLayoutManager(this, 4)
        gridLayoutManager.reverseLayout = true
        recyclerView.layoutManager = gridLayoutManager

        val adapter = StepAdapter(Status(ContractStatus.CREATED))
        recyclerView.adapter = adapter

        val customerDenyDelivery = ArrayList<EscalationTypeEnum>()
        customerDenyDelivery.add(EscalationTypeEnum.CUSTOMER_DENY_DELIVERY)

        val customerRejectItem = ArrayList<EscalationTypeEnum>()
        customerRejectItem.add(EscalationTypeEnum.CUSTOMER_REJECT_ITEM)

        val customerDenyDeliveryAndRejectItem = ArrayList<EscalationTypeEnum>()
        customerDenyDeliveryAndRejectItem.add(EscalationTypeEnum.CUSTOMER_DENY_DELIVERY)
        customerDenyDeliveryAndRejectItem.add(EscalationTypeEnum.CUSTOMER_REJECT_ITEM)

        findViewById<RadioGroup>(R.id.radioGroup1).setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioButton1 -> {
                    adapter.updateState(Status(ContractStatus.CREATED))
                }

                R.id.radioButton2 -> {
                    adapter.updateState(Status(ContractStatus.WAITING_FOR_DELIVERY))
                }

                R.id.radioButton3 -> {
                    adapter.updateState(Status(ContractStatus.ITEM_INSPECTION))
                }

                R.id.radioButton4 -> {
                    adapter.updateState(Status(ContractStatus.ITEM_ACCEPTED))
                }

                R.id.radioButton5 -> {
                    adapter.updateState(Status(ContractStatus.COMPLETED))
                }
            }
        }

        findViewById<RadioGroup>(R.id.radioGroup2).setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioButton6 -> {
                    adapter.updateState(Status(ContractStatus.SUBMITTED))
                }

                R.id.radioButton7 -> {
                    adapter.updateState(Status(ContractStatus.WAITING_FOR_DELIVERY))
                }

                R.id.radioButton8 -> {
                    adapter.updateState(
                        Status(
                            ContractStatus.DELIVERY_ESCALATION,customerDenyDelivery
                        )
                    )
                }

                R.id.radioButton9 -> {
                    adapter.updateState(
                        Status(
                            ContractStatus.ITEM_INSPECTION,
                            customerDenyDelivery
                        )
                    )
                }

                R.id.radioButton10 -> {
                    adapter.updateState(
                        Status(
                            ContractStatus.ITEM_ACCEPTED,
                            customerDenyDelivery
                        )
                    )
                }

                R.id.radioButton11 -> {
                    adapter.updateState(
                        Status(
                            ContractStatus.COMPLETED,
                            customerDenyDelivery
                        )
                    )
                }
            }
        }

        findViewById<RadioGroup>(R.id.radioGroup3).setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioButton12 -> {
                    adapter.updateState(Status(ContractStatus.PENDING_PAYMENT_VERIFICATION))
                }

                R.id.radioButton13 -> {
                    adapter.updateState(Status(ContractStatus.WAITING_FOR_DELIVERY))
                }

                R.id.radioButton14 -> {
                    adapter.updateState(
                        Status(
                            ContractStatus.DELIVERY_ESCALATION,
                            customerDenyDelivery
                        )
                    )
                }

                R.id.radioButton15 -> {
                    adapter.updateState(
                        Status(
                            ContractStatus.RESOLVED_RELEASE_PROVIDER,
                            customerDenyDelivery
                        )
                    )
                }

                R.id.radioButton16 -> {
                    adapter.updateState(
                        Status(
                            ContractStatus.CLOSED_RELEASE_PROVIDER,
                            customerDenyDelivery
                        )
                    )
                }
            }
        }

        findViewById<RadioGroup>(R.id.radioGroup4).setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioButton17 -> {
                    adapter.updateState(Status(ContractStatus.PENDING_PAYMENT_VERIFICATION))
                }

                R.id.radioButton18 -> {
                    adapter.updateState(Status(ContractStatus.WAITING_FOR_DELIVERY))
                }

                R.id.radioButton19 -> {
                    adapter.updateState(Status(ContractStatus.INSPECTION_ESCALATION))
                }

                R.id.radioButton20 -> {
                    adapter.updateState(
                        Status(
                            ContractStatus.INSPECTION_ESCALATION,
                            customerRejectItem
                        )
                    )
                }

                R.id.radioButton21 -> {
                    adapter.updateState(
                        Status(
                            ContractStatus.RESOLVED_REFUND_CUSTOMER,
                            customerRejectItem
                        )
                    )
                }

                R.id.radioButton22 -> {
                    adapter.updateState(
                        Status(
                            ContractStatus.CLOSED_REFUND_CUSTOMER,
                            customerRejectItem
                        )
                    )
                }
            }
        }

        findViewById<RadioGroup>(R.id.radioGroup5).setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioButton23 -> {
                    adapter.updateState(Status(ContractStatus.CREATED))
                }

                R.id.radioButton24 -> {
                    adapter.updateState(Status(ContractStatus.WAITING_FOR_DELIVERY))
                }

                R.id.radioButton25 -> {
                    adapter.updateState(
                        Status(
                            ContractStatus.DELIVERY_ESCALATION,
                            customerDenyDelivery
                        )
                    )
                }

                R.id.radioButton26 -> {
                    adapter.updateState(
                        Status(
                            ContractStatus.ITEM_INSPECTION,
                            customerDenyDelivery
                        )
                    )
                }

                R.id.radioButton27 -> {
                    adapter.updateState(
                        Status(
                            ContractStatus.INSPECTION_ESCALATION,
                            customerDenyDeliveryAndRejectItem
                        )
                    )
                }

                R.id.radioButton28 -> {
                    adapter.updateState(
                        Status(
                            ContractStatus.RESOLVED_RELEASE_PROVIDER,
                            customerDenyDeliveryAndRejectItem
                        )
                    )
                }

                R.id.radioButton29 -> {
                    adapter.updateState(
                        Status(
                            ContractStatus.CLOSED_RELEASE_PROVIDER,
                            customerDenyDeliveryAndRejectItem
                        )
                    )
                }
            }
        }

//        viewModel.remoteConfigRepo.initConfigs()
//        viewModel.enableBuyerCreateContractLiveData.observe(this) { updateConfigs(it) }
    }

//    private fun updateConfigs(boolean: Boolean) {
//
//        findViewById<Button>(R.id.hide_btn).isVisible = boolean
//        Toast.makeText(
//            this, "button visibility is $boolean", Toast.LENGTH_SHORT
//        ).show()
//
//    }


}

enum class ContractStatus {
    DRAFT, CREATED, WAITING_FOR_APPROVAL, ARCHIVED, CONTRACT_ACCEPTED, CONTRACT_REJECTED,
    PAID, Contract_Escalated, WAITING_FOR_SHIPMENT, WAITING_FOR_DELIVERY,
    DELIVERY_ESCALATION, ITEM_INSPECTION, ITEM_ACCEPTED,
    INSPECTION_ESCALATION, COMPLETED, CLOSED_RELEASE_PROVIDER, CLOSED_REFUND_CUSTOMER,
    CANCELED, DELETED, RESOLVED_REFUND_CUSTOMER, RESOLVED_RELEASE_PROVIDER,
    PENDING_APPROVAL, OUT_OF_STOCK, SUBMITTED, PENDING_PAYMENT_VERIFICATION, IN_PROGRESS,
    WAITING_FOR_SETTLEMENT_CLOSE, WAITING_FOR_SETTLEMENT_RESOLVE
}

enum class EscalationTypeEnum {
    CUSTOMER_DENY_DELIVERY, PROVIDER_ESCALATE_DELIVERY, CUSTOMER_REJECT_ITEM,
    PROVIDER_ESCALATE_ITEM, CUSTOMER_CHECK_ESCALATION, PROVIDER_CHECK_ESCALATION
}