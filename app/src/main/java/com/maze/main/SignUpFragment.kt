package com.maze.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.maze.R
import com.maze.databinding.SignupFragmentBinding
import com.maze.data.UserData
import com.maze.viewmodel.SignUpViewModel

class SignUpFragment : Fragment() {

    private val mTestTag = "SIGNUP_PAGE_FRAGMENT"

    private var _binding: SignupFragmentBinding? = null
    private var vehicleMakeModel: String = ""
    private var vehicleNumber: String = ""
    private val signUpViewModel: SignUpViewModel by lazy {
        ViewModelProvider(this@SignUpFragment)[SignUpViewModel::class.java]
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SignupFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
        navigate()
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.action_settings).isVisible = false
        super.onPrepareOptionsMenu(menu)
    }

    private fun setUpListeners() {
        binding.apply {
            vehicleMakeModelEditText.doOnTextChanged { text, _, _, _ ->
                if (!text.isNullOrBlank()) {
                    vehicleMakeModelEditText.error = null
                    vehicleMakeModel = vehicleMakeModelEditText.text.toString()
                }
            }

            vehicleNumberEditText.doOnTextChanged { text, _, _, _ ->
                if (!text.isNullOrBlank()) {
                    vehicleNumberEditText.error = null
                    vehicleNumber = vehicleNumberEditText.text.toString()
                }
            }

            submitButton.setOnClickListener {
                with(signUpViewModel) {
                    if (!vehicleMakeModelEditText.text.isNullOrBlank() && !vehicleNumberEditText.text.isNullOrBlank()) {
                        addUserToDatabase(this)
                    } else {
                        vehicleMakeModelEditText.error =
                            when (vehicleMakeModelEditText.text.isNullOrBlank()) {
                                true -> "Please Fill This Field"
                                else -> null
                            }
                        vehicleNumberEditText.error =
                            when (vehicleNumberEditText.text.isNullOrBlank()) {
                                true -> "Please Fill This Field"
                                else -> null
                            }
                    }
                }
            }
        }
    }

    private fun addUserToDatabase(signUpViewModel: SignUpViewModel) {
        if (!checkIfExists(vehicleNumber)) {
            var countID = 0
            val users = signUpViewModel.getAllUsers()
            if (users.count != 0) {
                countID = signUpViewModel.getLastRowId().toInt()
            }
            signUpViewModel.addUserToDatabase(
                UserData(
                    _id = (++countID).toLong(),
                    carMakeModel = vehicleMakeModel,
                    licencePlateNumber = vehicleNumber,
                    userSignedUp = 1
                ),
                requireContext()
            )
            Log.i(mTestTag, "User Added")
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun navigate() {
        with(signUpViewModel) {
            val user = getLastUser()
            if (user.count > 0) {
                if (user.getString(3).toInt() == 1) {
                    findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                }
            }
        }
    }

    private fun checkIfExists(vehicleNumber: String): Boolean {
        with(signUpViewModel) {
            return if (getAllUsers().count > 0) {
                when (getUserByVehicleNumber(vehicleNumber)) {
                    true -> {
                        Log.i(mTestTag, "USER ALREADY EXISTS")
                        true
                    }

                    else -> false
                }
            } else {
                false
            }
        }
    }

    private fun printAllUser() {
        val users = signUpViewModel.getAllUsers()
        users.moveToFirst()
        while (!users.isAfterLast) {
            Log.i(
                mTestTag,
                "User: ${users.getLong(0)} ${users.getString(1)} ${users.getString(2)} ${
                    users.getInt(
                        3
                    )
                }"
            )
            users.moveToNext()
        }
        users.close()
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).setActionBarTitle("Sign Up")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}