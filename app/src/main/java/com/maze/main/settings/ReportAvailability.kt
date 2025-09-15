package com.maze.main.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maze.R
import com.maze.database.SettingsData
import com.maze.databinding.FragmentReportAvailabilityBinding
import com.maze.databinding.FragmentSettingBinding
import com.maze.main.MainActivity


class ReportAvailability : Fragment() {

    private var _binding: FragmentReportAvailabilityBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReportAvailabilityBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onResume() {
        super.onResume()
        (activity as MainActivity).setActionBarTitle("Report Availability")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}