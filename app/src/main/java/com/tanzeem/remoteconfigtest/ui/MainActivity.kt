package com.tanzeem.remoteconfigtest.ui

import android.os.Bundle
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.tanzeem.remoteconfigtest.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val customAdapterView = findViewById<AdapterView>(R.id.customAdapterView)

        customAdapterView.setupAdapter(context = this, contractStatus = ContractStatus.CREATED)

        findViewById<RadioGroup>(R.id.radioGroup1).setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButton1 -> {
                    customAdapterView.updateState(contractStatus = ContractStatus.CREATED)
                }

                R.id.radioButton2 -> {
                    customAdapterView.updateState(contractStatus = ContractStatus.WAITING_FOR_DELIVERY)
                }

                R.id.radioButton3 -> {
                    customAdapterView.updateState(contractStatus = ContractStatus.ITEM_INSPECTION)
                }

                R.id.radioButton4 -> {
                    customAdapterView.updateState(contractStatus = ContractStatus.ITEM_ACCEPTED)
                }

                R.id.radioButton5 -> {
                    customAdapterView.updateState(contractStatus = ContractStatus.COMPLETED)
                }
            }
        }

        findViewById<RadioGroup>(R.id.radioGroup2).setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButton6 -> {
                    customAdapterView.updateState(contractStatus = ContractStatus.SUBMITTED)
                }

                R.id.radioButton7 -> {
                    customAdapterView.updateState(contractStatus = ContractStatus.WAITING_FOR_DELIVERY)
                }

                R.id.radioButton8 -> {
                    customAdapterView.updateState(
                        contractStatus = ContractStatus.DELIVERY_ESCALATION,
                        escalationDelivery = EscalationTypeEnum.CUSTOMER_DENY_DELIVERY
                    )
                }

                R.id.radioButton9 -> {
                    customAdapterView.updateState(
                        contractStatus = ContractStatus.ITEM_INSPECTION,
                        escalationDelivery = EscalationTypeEnum.CUSTOMER_DENY_DELIVERY
                    )
                }

                R.id.radioButton10 -> {
                    customAdapterView.updateState(
                        contractStatus = ContractStatus.ITEM_ACCEPTED,
                        escalationDelivery = EscalationTypeEnum.CUSTOMER_DENY_DELIVERY
                    )
                }

                R.id.radioButton11 -> {
                    customAdapterView.updateState(
                        contractStatus = ContractStatus.COMPLETED,
                        escalationDelivery = EscalationTypeEnum.CUSTOMER_DENY_DELIVERY
                    )
                }
            }
        }

        findViewById<RadioGroup>(R.id.radioGroup3).setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButton12 -> {
                    customAdapterView.updateState(contractStatus = ContractStatus.PENDING_PAYMENT_VERIFICATION)
                }

                R.id.radioButton13 -> {
                    customAdapterView.updateState(contractStatus = ContractStatus.WAITING_FOR_DELIVERY)
                }

                R.id.radioButton14 -> {
                    customAdapterView.updateState(
                        contractStatus = ContractStatus.DELIVERY_ESCALATION,
                        escalationDelivery = EscalationTypeEnum.PROVIDER_ESCALATE_DELIVERY

                    )
                }

                R.id.radioButton15 -> {
                    customAdapterView.updateState(
                        contractStatus = ContractStatus.RESOLVED_RELEASE_PROVIDER,
                        escalationDelivery = EscalationTypeEnum.PROVIDER_ESCALATE_DELIVERY
                    )
                }

                R.id.radioButton16 -> {
                    customAdapterView.updateState(
                        contractStatus = ContractStatus.CLOSED_RELEASE_PROVIDER,
                        escalationDelivery = EscalationTypeEnum.PROVIDER_ESCALATE_DELIVERY
                    )
                }
            }
        }

        findViewById<RadioGroup>(R.id.radioGroup4).setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButton17 -> {
                    customAdapterView.updateState(contractStatus = ContractStatus.PENDING_PAYMENT_VERIFICATION)
                }

                R.id.radioButton18 -> {
                    customAdapterView.updateState(contractStatus = ContractStatus.WAITING_FOR_DELIVERY)
                }

                R.id.radioButton19 -> {
                    customAdapterView.updateState(contractStatus = ContractStatus.ITEM_INSPECTION)
                }

                R.id.radioButton20 -> {
                    customAdapterView.updateState(
                        contractStatus = ContractStatus.INSPECTION_ESCALATION,
                        escalationItem = EscalationTypeEnum.CUSTOMER_REJECT_ITEM
                    )
                }

                R.id.radioButton21 -> {
                    customAdapterView.updateState(
                        contractStatus = ContractStatus.RESOLVED_REFUND_CUSTOMER,
                        escalationItem = EscalationTypeEnum.CUSTOMER_REJECT_ITEM
                    )
                }

                R.id.radioButton22 -> {
                    customAdapterView.updateState(
                        contractStatus = ContractStatus.CLOSED_REFUND_CUSTOMER,
                        escalationItem = EscalationTypeEnum.CUSTOMER_REJECT_ITEM
                    )
                }
            }
        }

        findViewById<RadioGroup>(R.id.radioGroup5).setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButton23 -> {
                    customAdapterView.updateState(contractStatus = ContractStatus.CREATED)
                }

                R.id.radioButton24 -> {
                    customAdapterView.updateState(contractStatus = ContractStatus.WAITING_FOR_DELIVERY)
                }

                R.id.radioButton25 -> {
                    customAdapterView.updateState(
                        contractStatus = ContractStatus.DELIVERY_ESCALATION,
                        escalationDelivery = EscalationTypeEnum.PROVIDER_ESCALATE_DELIVERY
                    )
                }

                R.id.radioButton26 -> {
                    customAdapterView.updateState(
                        contractStatus = ContractStatus.ITEM_INSPECTION,
                        escalationDelivery = EscalationTypeEnum.PROVIDER_ESCALATE_DELIVERY
                    )
                }

                R.id.radioButton27 -> {
                    customAdapterView.updateState(
                        contractStatus = ContractStatus.INSPECTION_ESCALATION,
                        escalationDelivery = EscalationTypeEnum.PROVIDER_ESCALATE_DELIVERY,
                        escalationItem = EscalationTypeEnum.PROVIDER_ESCALATE_ITEM
                    )
                }

                R.id.radioButton28 -> {
                    customAdapterView.updateState(
                        contractStatus = ContractStatus.RESOLVED_RELEASE_PROVIDER,
                        escalationDelivery = EscalationTypeEnum.PROVIDER_ESCALATE_DELIVERY,
                        escalationItem = EscalationTypeEnum.PROVIDER_ESCALATE_ITEM
                    )
                }

                R.id.radioButton29 -> {
                    customAdapterView.updateState(
                        contractStatus = ContractStatus.CLOSED_RELEASE_PROVIDER,
                        escalationDelivery = EscalationTypeEnum.PROVIDER_ESCALATE_DELIVERY,
                        escalationItem = EscalationTypeEnum.PROVIDER_ESCALATE_ITEM
                    )
                }
            }
        }
    }
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