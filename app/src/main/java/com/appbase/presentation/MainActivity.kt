package com.appbase.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.appbase.R
import com.appbase.databinding.ActivityMainBinding
import com.appbase.domain.model.AppointmentsResponse
import com.appbase.utils.Constants
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var doctorAdapter: AppointmentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        processJson()
        setUpObservers()
        viewModel.getDoctorNames()
    }

    private fun setUpObservers() {
        viewModel.doctorNames.observe(this) {
            setUpRecyclerView(it)
        }
    }

    private fun setUpRecyclerView(doctorList: List<String>) {

        doctorAdapter = AppointmentsAdapter(doctorList, this)
        binding.rvDoctorList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = doctorAdapter
        }
    }

    private fun processJson() {
        val inputStream = this.resources.openRawResource(R.raw.appointments)
        val jsonString = inputStream.bufferedReader().use { it.readText() }

        val gson = Gson()
        val response = gson.fromJson(jsonString, AppointmentsResponse::class.java)

        viewModel.appointments = ArrayList(response.appointments)
    }

    override fun onClickDoctorItem(position: Int, doctorName: String) {

        startActivity(Intent(this, AppointmentDetailsActivity::class.java).apply {
            putParcelableArrayListExtra(
                Constants.APPOINTMENT_DATA,
                ArrayList(viewModel.getAppointments(doctorName))
            )
        })
    }
}