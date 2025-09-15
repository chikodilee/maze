package com.maze.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserData(
    val _id: Long,
    val carMakeModel: String,
    val licencePlateNumber: String,
    val userSignedUp: Int
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserData

        if (_id != other._id) return false
        if (carMakeModel != other.carMakeModel) return false
        if (licencePlateNumber != other.licencePlateNumber) return false
        if (userSignedUp != other.userSignedUp) return false

        return true
    }

    override fun hashCode(): Int {
        var result = _id.hashCode()
        result = 31 * result + carMakeModel.hashCode()
        result = 31 * result + licencePlateNumber.hashCode()
        result = 31 * result + userSignedUp.hashCode()
        return result
    }
}