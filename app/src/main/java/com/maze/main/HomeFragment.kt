package com.maze.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.maze.R
import com.maze.databinding.FragmentHomeBinding
import com.maze.notification.NotificationHandler
import com.google.android.gms.common.api.Status
import com.google.android.gms.common.util.CollectionUtils.listOf
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val TAG = "HomeFragment"

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showNotification()
        initializeSearch()
    }

    private fun initializeSearch() {
        val key = resources.getString(R.string.api_key)

        if (!Places.isInitialized()) {
            Places.initialize(requireContext(), key)
        }

        val placesClient = Places.createClient(requireContext())

        val autocompleteFragment =
            childFragmentManager.findFragmentById(R.id.autocomplete_fragment)
                    as AutocompleteSupportFragment

        val searchIcon: ImageView =
            (autocompleteFragment.view as LinearLayout).getChildAt(0) as ImageView
        searchIcon.setImageDrawable(resources.getDrawable(R.drawable.search_icon))


        autocompleteFragment.setPlaceFields(
            listOf(
                Place.Field.ID,
                Place.Field.NAME,
                Place.Field.LAT_LNG
            )
        )

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                Log.i(TAG, "Place: ${place.name}, ${place.id}")
                Log.i(TAG, "Place: Long ${place.latLng?.longitude}, Lat ${place.latLng?.latitude}")
                autocompleteFragment.setText(place.name)
            }

            override fun onError(status: Status) {
                Log.i(TAG, "An error occurred: $status")
            }
        })

        autocompleteFragment.apply {
            setHint("Address, Place, City or Venue")
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).setActionBarTitle("Navigation")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showNotification() {
        binding.fab.setOnClickListener { _ ->
            val notificationHandler = NotificationHandler(requireActivity())
            Log.i(TAG, "showNotification")
            notificationHandler
                .createNotification(
                    MainActivity.notificationLabel, MainActivity.notificationDescription
                )
        }
    }
}