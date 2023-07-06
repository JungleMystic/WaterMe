package com.lrm.waterme.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.lrm.waterme.R
import com.lrm.waterme.viewmodel.PlantViewModel
import com.lrm.waterme.viewmodel.PlantViewModelFactory
import java.util.concurrent.TimeUnit

class ReminderDialogFragment(private val plantName: String): DialogFragment() {

    private val viewModel: PlantViewModel by viewModels {
        PlantViewModelFactory(requireActivity().application)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
                .setTitle(R.string.water_reminder)
                .setItems(R.array.water_schedule_array) {_, position ->
                    when(position) {
                        0 -> viewModel.scheduleReminder(5, TimeUnit.SECONDS, plantName)
                        1 -> viewModel.scheduleReminder(1, TimeUnit.DAYS, plantName)
                        2 -> viewModel.scheduleReminder(7, TimeUnit.DAYS, plantName)
                        3 -> viewModel.scheduleReminder(30, TimeUnit.DAYS, plantName)
                    }
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}