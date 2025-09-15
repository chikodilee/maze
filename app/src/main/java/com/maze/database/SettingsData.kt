package com.maze.database

import androidx.annotation.StringRes
import com.google.android.gms.common.util.CollectionUtils.listOf
import com.maze.R

class SettingsData {

    fun loadSettingOptions(): List<SettingOption>{
        return listOf(
            SettingOption(R.string.UserProfile),
            SettingOption(R.string.ViolationInfo),
            SettingOption(R.string.Violation),
            SettingOption(R.string.Availablity),
            SettingOption(R.string.Help)
        )
    }
}

data class SettingOption(
    @StringRes val stringResourceId: Int
)