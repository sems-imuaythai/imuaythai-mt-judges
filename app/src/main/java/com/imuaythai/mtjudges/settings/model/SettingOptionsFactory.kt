package com.imuaythai.mtjudges.settings.model

import android.content.Context
import com.imuaythai.mtjudges.change.settings.model.SettingsListItem

interface SettingOptionsFactory {

    fun createOptions(context: Context) : List<SettingsListItem> = ArrayList()

}