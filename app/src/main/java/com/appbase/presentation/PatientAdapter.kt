package com.appbase.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appbase.R
import com.appbase.databinding.LayoutPatientListItemBinding
import com.appbase.domain.model.AppointmentsData

class PatientAdapter(private val appointmentList: ArrayList<AppointmentsData>?) :
    RecyclerView.Adapter<PatientAdapter.PatientViewHolder>() {
    inner class PatientViewHolder(private val binding: LayoutPatientListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(appointmentsData: AppointmentsData?) {
            appointmentsData?.let {
                binding.apply {
                    tvPatientName.text = "Name: ${it.patientName}"
                    tvReason.text = "Reason: ${it.reason}"
                    tvSlot.text = "Slot: ${it.date} ${it.time}"
                    tvStatus.apply {
                        text = "${it.status}"
                        when (it.status) {
                            "Confirmed" -> setTextColor(context.getColor(R.color.green))
                            "Pending" -> setTextColor(context.getColor(R.color.yellow))
                            "Cancelled" -> setTextColor(context.getColor(R.color.red))
                        }
                    }
                    tvResourceName.text = "Resources: \n ${it.resourceName}"
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        return PatientViewHolder(
            LayoutPatientListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = appointmentList?.size ?: 0

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        holder.bind(appointmentList?.get(position))
    }


}