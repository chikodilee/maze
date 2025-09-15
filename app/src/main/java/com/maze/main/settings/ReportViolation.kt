package com.maze.main.settings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maze.R
import com.maze.database.ReportViolationData
import com.maze.database.SettingsData
import com.maze.databinding.FragmentReportViolationBinding
import com.maze.databinding.FragmentSettingBinding
import com.maze.main.MainActivity
import com.google.android.material.textfield.TextInputEditText

class ReportViolation : Fragment() {
    private var _binding: FragmentReportViolationBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReportViolationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataSet = ReportViolationData().loadReportViolationData()
        val recyclerView = binding.recyclerViewReportViolation

        recyclerView.adapter = ReportViolationAdapter(binding, dataSet)
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).setActionBarTitle("Report Violation")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}