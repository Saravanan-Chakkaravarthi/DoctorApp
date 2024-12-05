package com.appbase.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appbase.databinding.LayoutDoctorListItemBinding

class AppointmentsAdapter(
    private val doctorList: List<String>, private val listener: OnClickListener
) : RecyclerView.Adapter<AppointmentsAdapter.AppointmentsViewHolder>() {
    inner class AppointmentsViewHolder(private val binding: LayoutDoctorListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(doctorName: String) {
            binding.tvDoctorName.text = doctorName
            binding.root.setOnClickListener {
                listener.onClickDoctorItem(adapterPosition, doctorName)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentsViewHolder {
        return AppointmentsViewHolder(
            LayoutDoctorListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = doctorList.size

    override fun onBindViewHolder(holder: AppointmentsViewHolder, position: Int) {
        holder.bind(doctorList[position])

    }
}

interface OnClickListener {
    fun onClickDoctorItem(position: Int, doctorName: String)
}