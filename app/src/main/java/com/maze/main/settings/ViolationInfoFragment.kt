package com.maze.main.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maze.R

class ViolationInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.violation_info, container, false)
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ViolationInfoFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}