package com.maze.main.settings

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.maze.R
import com.maze.database.SettingOption
import com.maze.databinding.SettingOptionsBinding

class SettingsAdapter(
    private val context: Context?,
    private val dataSet: List<SettingOption>
) : RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>() {

    class SettingsViewHolder(val settingsOptionsBinding: SettingOptionsBinding) :
        RecyclerView.ViewHolder(settingsOptionsBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder =
        SettingsViewHolder(
            SettingOptionsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        val settingOption: SettingOption = dataSet[position]
        val item = dataSet[position]

        holder.settingsOptionsBinding.apply {
            optionName.text = context?.getString(settingOption.stringResourceId)
        }

        holder.itemView.setOnClickListener {
            when (settingOption.stringResourceId) {
                SettingOption(R.string.Violation).stringResourceId -> {
                    it.findNavController().navigate(R.id.action_settingsFragment_to_reportViolation)
                }
                SettingOption(R.string.Availablity).stringResourceId -> {
                    it.findNavController()
                        .navigate(R.id.action_settingsFragment_to_reportAvailability)
                }
                SettingOption(R.string.ViolationInfo).stringResourceId -> {
                    it.findNavController().navigate(R.id.action_setting_violation_to_violationInfo)
                }
            }
        }
    }

    override fun getItemCount() = dataSet.size

}