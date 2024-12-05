package com.appbase.presentation

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.appbase.databinding.ActivityAppointmentDetailsBinding
import com.appbase.domain.model.AppointmentsData
import com.appbase.utils.Constants
import com.google.gson.Gson

class AppointmentDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAppointmentDetailsBinding
    private var appointmentsData: ArrayList<AppointmentsData>? = null
    private lateinit var patientAdapter: PatientAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAppointmentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getBundleData()
        setUpUI()
    }

    private fun setUpUI() {
        setUpToolBar()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        patientAdapter = PatientAdapter(appointmentsData)
        binding.rvPatientList.apply {
            layoutManager = LinearLayoutManager(this@AppointmentDetailsActivity)
            adapter = patientAdapter
        }
    }

    private fun getBundleData() {
        appointmentsData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableArrayListExtra(
                Constants.APPOINTMENT_DATA,
                AppointmentsData::class.java
            )
        } else {
            intent.getParcelableArrayListExtra(Constants.APPOINTMENT_DATA)
        }
    }

    private fun setUpToolBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.apply {
            title = appointmentsData?.first()?.doctorName
            setNavigationOnClickListener {
                finish()
            }
        }
    }
}
