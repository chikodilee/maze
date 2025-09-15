package com.maze.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.maze.R
import com.maze.database.SettingOption
import com.maze.database.SettingsData
import com.maze.databinding.FragmentSettingBinding
import com.maze.main.settings.SettingsAdapter
import com.maze.main.settings.ViolationInfoFragment


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataSet = SettingsData().loadSettingOptions()
        val recyclerView = binding.recyclerViewSettings
        recyclerView.adapter = SettingsAdapter(context,dataSet)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.action_settings).isVisible = false
        super.onPrepareOptionsMenu(menu)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).setActionBarTitle("Settings")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(param1: String, param2: String): SettingsFragment {
            val fragment = SettingsFragment()
            val args = Bundle().apply {
                putString("ARG_PARAM1", param1)
                putString("ARG_PARAM2", param2)
            }
            fragment.arguments = args
            return fragment
        }
    }
}