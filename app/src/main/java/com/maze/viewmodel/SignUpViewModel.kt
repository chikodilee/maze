package com.maze.viewmodel

import android.app.Application
import android.content.Context
import android.database.Cursor
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.maze.database.MyDatabaseHelper
import com.maze.data.UserData

class SignUpViewModel(application: Application) : AndroidViewModel(application) {

    private val databaseHelper: MyDatabaseHelper by lazy {
        MyDatabaseHelper(application.applicationContext)
    }

    fun addUserToDatabase(userData: UserData, context: Context) {
        databaseHelper.insertUserInformation(userData, context)
    }

    fun getLastRowId(): Long {
        val lastRow = databaseHelper.getLastRowID()
        lastRow.moveToFirst()
        return lastRow.getLong(0)
    }

    fun getLastUser(): Cursor {
        val lastRow = databaseHelper.getLastRowID()
        lastRow.moveToFirst()
        return lastRow
    }

    fun getAllUsers(): Cursor {
        return databaseHelper.getAllUsers()
    }

    fun getUserByVehicleNumber(vehicleNumber: String): Boolean {
        val vehicle = databaseHelper.findUserByLicensePlate(vehicleNumber)
        vehicle.moveToNext()
        return when (vehicle.count > 0) {
            true -> vehicle.getString(2).isNotEmpty()
            else -> false
        }
    }

    fun deleteAllUsers() {
        databaseHelper.deleteAllUsers()
    }

    fun deleteUserByLicencePlateNumber(vehicleNumber: String) {
        databaseHelper.deleteUserByLicencePlate(vehicleNumber)
    }
}