package com.tanzeem.remoteconfigtest.ui


object AdapterHelper {

    fun getCurrentStep(contractStatus: ContractStatus): Int {
        return when (contractStatus) {
            ContractStatus.CREATED, ContractStatus.SUBMITTED, ContractStatus.PENDING_PAYMENT_VERIFICATION -> 0
            ContractStatus.WAITING_FOR_DELIVERY, ContractStatus.DELIVERY_ESCALATION -> 1
            ContractStatus.ITEM_INSPECTION, ContractStatus.INSPECTION_ESCALATION -> 2
            ContractStatus.ITEM_ACCEPTED, ContractStatus.RESOLVED_RELEASE_PROVIDER, ContractStatus.RESOLVED_REFUND_CUSTOMER -> 3
            ContractStatus.COMPLETED, ContractStatus.CLOSED_RELEASE_PROVIDER, ContractStatus.CLOSED_REFUND_CUSTOMER -> 4
            else -> 0
        }
    }

    fun isClosedResolvedEscalation(contractStatus: ContractStatus): Boolean {
        return (contractStatus == ContractStatus.RESOLVED_RELEASE_PROVIDER || contractStatus == ContractStatus.RESOLVED_REFUND_CUSTOMER ||
                contractStatus == ContractStatus.CLOSED_RELEASE_PROVIDER || contractStatus == ContractStatus.CLOSED_REFUND_CUSTOMER)
    }

    fun isEscalationDelivery(escalationDelivery: EscalationTypeEnum?): Boolean {
        return (escalationDelivery == EscalationTypeEnum.CUSTOMER_DENY_DELIVERY || escalationDelivery == EscalationTypeEnum.PROVIDER_ESCALATE_DELIVERY)

    }

    fun isEscalationItem(escalationItem: EscalationTypeEnum?): Boolean {
        return (escalationItem == EscalationTypeEnum.CUSTOMER_REJECT_ITEM || escalationItem == EscalationTypeEnum.PROVIDER_ESCALATE_ITEM)
    }
}