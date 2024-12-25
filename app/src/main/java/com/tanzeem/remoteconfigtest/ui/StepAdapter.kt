package com.tanzeem.remoteconfigtest.ui

import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.tanzeem.remoteconfigtest.R

class StepAdapter(private var status: Status) : RecyclerView.Adapter<StepAdapter.StepViewHolder>() {

    class StepViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val stepNumber: TextView = view.findViewById(R.id.step_number)
        val stepText: TextView = view.findViewById(R.id.step_text)
        val stepLine1: View = view.findViewById(R.id.step_line1)
        val stepLine2: View = view.findViewById(R.id.step_line2)
    }

    private fun getCurrentStep(): Int {
        when (status.contractStatus) {
            ContractStatus.CREATED, ContractStatus.SUBMITTED, ContractStatus.PENDING_PAYMENT_VERIFICATION -> {
                return 0
            }

            ContractStatus.WAITING_FOR_DELIVERY, ContractStatus.DELIVERY_ESCALATION -> {
                return 1
            }

            ContractStatus.ITEM_INSPECTION, ContractStatus.INSPECTION_ESCALATION -> {
                return 2
            }

            ContractStatus.ITEM_ACCEPTED, ContractStatus.RESOLVED_RELEASE_PROVIDER, ContractStatus.RESOLVED_REFUND_CUSTOMER -> {
                return 3
            }

            ContractStatus.COMPLETED, ContractStatus.CLOSED_RELEASE_PROVIDER, ContractStatus.CLOSED_REFUND_CUSTOMER -> {
                return 4
            }

            else -> return 0
        }
    }

    private fun getClosedResolvedEscalationDelivery(): Boolean {
        return (status.contractStatus == ContractStatus.RESOLVED_RELEASE_PROVIDER || status.contractStatus == ContractStatus.RESOLVED_REFUND_CUSTOMER ||
                status.contractStatus == ContractStatus.CLOSED_RELEASE_PROVIDER || status.contractStatus == ContractStatus.CLOSED_REFUND_CUSTOMER)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.step_item, parent, false)
        return StepViewHolder(view)
    }

    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {

        val step = steps[position]
        holder.stepNumber.text = step.number.toString()
        holder.stepText.text = step.title
        when {
            position < getCurrentStep() -> {
                if (status.escalationTypeEnum?.contains(EscalationTypeEnum.CUSTOMER_DENY_DELIVERY) == true && position == 1) {
                    holder.stepNumber.background = ContextCompat.getDrawable(
                        holder.itemView.context,
                        R.drawable.circle_background_escalation
                    )
                    holder.stepNumber.setTextColor(Color.WHITE)
                    holder.stepText.setTextColor(
                        ContextCompat.getColor(holder.itemView.context, R.color.black)
                    )
                    holder.stepText.text = "التنفيذ \n تم التحكيم"
                } else if (status.escalationTypeEnum?.contains(EscalationTypeEnum.CUSTOMER_REJECT_ITEM) == true && getClosedResolvedEscalationDelivery() && position == 2) {
                    holder.stepNumber.background = ContextCompat.getDrawable(
                        holder.itemView.context, R.drawable.circle_background_escalation
                    )
                    holder.stepNumber.setTextColor(Color.WHITE)
                    holder.stepText.setTextColor(
                        ContextCompat.getColor(holder.itemView.context, R.color.black)
                    )
                    holder.stepText.text = "الفحص \n تم التحكيم"
                } else if (status.escalationTypeEnum?.contains(EscalationTypeEnum.CUSTOMER_DENY_DELIVERY) == true && getClosedResolvedEscalationDelivery() && position == 2) {
                    holder.stepNumber.background = ContextCompat.getDrawable(
                        holder.itemView.context, R.drawable.circle_background_escalation
                    )
                    holder.stepNumber.setTextColor(Color.WHITE)
                    holder.stepText.setTextColor(
                        ContextCompat.getColor(holder.itemView.context, R.color.black)
                    )
                    holder.stepNumber.text = "-"
                    holder.stepText.paintFlags =
                        holder.stepText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    holder.stepNumber.background = ContextCompat.getDrawable(
                        holder.itemView.context, R.drawable.circle_background_green
                    )
                    holder.stepNumber.setTextColor(Color.WHITE)
                    holder.stepText.setTextColor(
                        ContextCompat.getColor(holder.itemView.context, R.color.green)
                    )
                }
            }

            position == getCurrentStep() -> {
                if (status.escalationTypeEnum?.contains(EscalationTypeEnum.CUSTOMER_DENY_DELIVERY) == true && position == 1) {
                    holder.stepNumber.background = ContextCompat.getDrawable(
                        holder.itemView.context, R.drawable.circle_background_escalation
                    )
                    holder.stepNumber.setTextColor(Color.WHITE)
                    holder.stepText.setTextColor(
                        ContextCompat.getColor(holder.itemView.context, R.color.black)
                    )
                    holder.stepText.text = "التنفيذ \n جاري التحكيم"
                } else if (status.escalationTypeEnum?.contains(EscalationTypeEnum.CUSTOMER_REJECT_ITEM) == true && position == 2) {
                    holder.stepNumber.background = ContextCompat.getDrawable(
                        holder.itemView.context, R.drawable.circle_background_escalation
                    )
                    holder.stepNumber.setTextColor(Color.WHITE)
                    holder.stepText.setTextColor(
                        ContextCompat.getColor(holder.itemView.context, R.color.black)
                    )
                    holder.stepText.text = "الفحص \n جاري التحكيم"
                } else {
                    holder.stepNumber.background = ContextCompat.getDrawable(
                        holder.itemView.context, R.drawable.circle_background_black
                    )
                    holder.stepNumber.setTextColor(Color.WHITE)
                    holder.stepText.setTextColor(
                        ContextCompat.getColor(holder.itemView.context, R.color.black)
                    )
                }
            }

            else -> {
                holder.stepNumber.background = ContextCompat.getDrawable(
                    holder.itemView.context, R.drawable.circle_background_grey
                )
                holder.stepNumber.setTextColor(Color.BLACK)
                holder.stepText.setTextColor(
                    ContextCompat.getColor(holder.itemView.context, R.color.gray)
                )
            }
        }

        holder.stepLine1.visibility = if (position == 0) View.INVISIBLE else View.VISIBLE
        holder.stepLine2.visibility =
            if (position == 3) View.INVISIBLE else View.VISIBLE
    }

    override fun getItemCount(): Int = 4

    fun updateState(status: Status) {
        this.status = status
        notifyDataSetChanged()
    }

}

data class Step(val number: Int, val title: String)

data class Status(
    var contractStatus: ContractStatus,
    var escalationTypeEnum: ArrayList<EscalationTypeEnum>? = null
)

val steps = listOf(
    Step(1, "الدفع"),
    Step(2, "التنفيذ"),
    Step(3, "الفحص"),
    Step(4, "إنهاء المعاملة")
)