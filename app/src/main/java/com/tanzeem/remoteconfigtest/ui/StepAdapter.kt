package com.tanzeem.remoteconfigtest.ui

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.tanzeem.remoteconfigtest.R

class StepAdapter(
    private val context: Context,
    private var contractStatus: ContractStatus,
    private var escalationDelivery: EscalationTypeEnum? = null,
    private var escalationItem: EscalationTypeEnum? = null
) : RecyclerView.Adapter<StepAdapter.StepViewHolder>() {

    class StepViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val stepNumber: TextView = view.findViewById(R.id.step_number)
        val stepText: TextView = view.findViewById(R.id.step_text)
        val stepLine1: View = view.findViewById(R.id.step_line1)
        val stepLine2: View = view.findViewById(R.id.step_line2)
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
            position < AdapterHelper.getCurrentStep(contractStatus) -> {
                when {
                    position == 1 && AdapterHelper.isEscalationDelivery(escalationDelivery) -> {
                        drawEscalationItem(holder)
                        holder.stepText.text = context.getString(R.string.execution_arbitrated)
                    }

                    position == 2 && AdapterHelper.isEscalationItem(escalationItem)
                            && AdapterHelper.isClosedResolvedEscalation(contractStatus) -> {
                        drawEscalationItem(holder)
                        holder.stepText.text = context.getString(R.string.inspection_arbitrated)
                    }

                    position == 2 && AdapterHelper.isEscalationDelivery(escalationDelivery)
                            && AdapterHelper.isClosedResolvedEscalation(contractStatus) -> {
                        drawEscalationItem(holder)
                        holder.stepNumber.text = "-"
                        holder.stepText.paintFlags =
                            holder.stepText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    }

                    else -> {
                        drawPreviousItem(holder)
                    }
                }
            }

            position == AdapterHelper.getCurrentStep(contractStatus) -> {
                when {
                    position == 1 && AdapterHelper.isEscalationDelivery(escalationDelivery) -> {
                        drawEscalationItem(holder)
                        holder.stepText.text = context.getString(R.string.execution_arbitration)
                    }

                    position == 2 && AdapterHelper.isEscalationItem(escalationItem) -> {
                        drawEscalationItem(holder)
                        holder.stepText.text = context.getString(R.string.inspection_arbitration)
                    }

                    else -> {
                        drawCurrentItem(holder)
                    }
                }
            }

            else -> {
                drawUpcomingItem(holder)
            }
        }

        holder.stepLine1.visibility = if (position == 0) View.INVISIBLE else View.VISIBLE
        holder.stepLine2.visibility = if (position == 3) View.INVISIBLE else View.VISIBLE
    }

    override fun getItemCount(): Int = steps.size

    fun updateState(
        contractStatus: ContractStatus,
        escalationDelivery: EscalationTypeEnum? = null,
        escalationItem: EscalationTypeEnum? = null
    ) {
        this.contractStatus = contractStatus
        this.escalationDelivery = escalationDelivery
        this.escalationItem = escalationItem
        notifyDataSetChanged()
    }

    private fun drawPreviousItem(holder: StepViewHolder) {
        holder.stepNumber.background =
            ContextCompat.getDrawable(context, R.drawable.circle_background_green)
        holder.stepNumber.setTextColor(ContextCompat.getColor(context, R.color.white))
        holder.stepText.setTextColor(ContextCompat.getColor(context, R.color.green))
    }

    private fun drawCurrentItem(holder: StepViewHolder) {
        holder.stepNumber.background =
            ContextCompat.getDrawable(context, R.drawable.circle_background_black)
        holder.stepNumber.setTextColor(ContextCompat.getColor(context, R.color.white))
        holder.stepText.setTextColor(ContextCompat.getColor(context, R.color.black))
    }

    private fun drawUpcomingItem(holder: StepViewHolder) {
        holder.stepNumber.background =
            ContextCompat.getDrawable(context, R.drawable.circle_background_grey)
        holder.stepNumber.setTextColor(ContextCompat.getColor(context, R.color.black))
        holder.stepText.setTextColor(ContextCompat.getColor(context, R.color.gray))
    }

    private fun drawEscalationItem(holder: StepViewHolder) {
        holder.stepNumber.background =
            ContextCompat.getDrawable(context, R.drawable.circle_background_escalation)
        holder.stepNumber.setTextColor(ContextCompat.getColor(context, R.color.white))
        holder.stepText.setTextColor(ContextCompat.getColor(context, R.color.black))
    }

    private val steps = listOf(
        Step(1, context.getString(R.string.payment)),
        Step(2, context.getString(R.string.execution)),
        Step(3, context.getString(R.string.inspection)),
        Step(4, context.getString(R.string.ending))
    )

    data class Step(val number: Int, val title: String)
}

