package com.appbase.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class AppointmentsResponse(
    val appointments: List<AppointmentsData>
)

@Parcelize
data class AppointmentsData(
    val id: String,
    val patientName: String,
    val doctorName: String,
    val date: String,
    val time: String,
    val reason: String,
    val status: String,
    val resourceName: String,
): Parcelable
