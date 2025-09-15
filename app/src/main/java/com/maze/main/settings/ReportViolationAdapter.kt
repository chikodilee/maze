package com.maze.main.settings

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.maze.R
import com.maze.database.SettingOption
import com.maze.database.ViolationData
import com.maze.databinding.FragmentReportViolationBinding
import com.maze.databinding.ReportViolationOptionsBinding
import com.google.android.material.textfield.TextInputEditText

class ReportViolationAdapter(
    private val binding: FragmentReportViolationBinding,
    private val dataSet: List<ViolationData>

) : RecyclerView.Adapter<ReportViolationAdapter.ReportViolationViewHolder>() {

    class ReportViolationViewHolder(val reportViolationOptionsBindng: ReportViolationOptionsBinding) :
        RecyclerView.ViewHolder(reportViolationOptionsBindng.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViolationViewHolder =
        ReportViolationViewHolder(
            ReportViolationOptionsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    override fun onBindViewHolder(holder: ReportViolationViewHolder, position: Int) {
        val violationData : ViolationData = dataSet[position]
        val item = dataSet[position]
        holder.reportViolationOptionsBindng.apply {
            optionName.text = violationData.address
        }

        holder.itemView.setOnClickListener {
            // Address
            binding.parkingAddressModelEditText.setText(violationData.address)
            // Time
            binding.timeOfViolationEditText.setText(violationData.time)
        }
    }

    override fun getItemCount() = dataSet.size


    interface ItemClickListener {
        fun onItemClick(position: SettingOption)
    }

}