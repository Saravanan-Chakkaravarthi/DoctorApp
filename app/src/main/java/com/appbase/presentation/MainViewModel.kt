package com.appbase.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appbase.domain.model.AppointmentsData
import com.appbase.domain.usecase.AppUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appUseCase: AppUseCase
) : ViewModel() {


    private val _doctorNames = MutableLiveData<List<String>>()
    val doctorNames: LiveData<List<String>> = _doctorNames

    var appointments: ArrayList<AppointmentsData> = arrayListOf()


    fun getDoctorNames() {
        _doctorNames.value = appointments.map { it.doctorName }.distinct().sorted()
    }

    fun getAppointments(doctorName: String): List<AppointmentsData> {
        return appointments.filter { it.doctorName == doctorName }.sortedBy { it.patientName }
    }
}