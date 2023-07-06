package com.lrm.waterme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.lrm.waterme.adapter.PlantAdapter
import com.lrm.waterme.databinding.ActivityMainBinding
import com.lrm.waterme.ui.ReminderDialogFragment
import com.lrm.waterme.viewmodel.PlantViewModel
import com.lrm.waterme.viewmodel.PlantViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: PlantViewModel by viewModels{
        PlantViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recyclerView
        val adapter = PlantAdapter(PlantAdapter.PlantListener { plant ->
            val dialog = ReminderDialogFragment(plantName = plant.name)
            dialog.show(supportFragmentManager, "WaterReminderDialogFragment")
            true
        })
        recyclerView.adapter = adapter
        val data = viewModel.plants
        adapter.submitList(data)
    }
}