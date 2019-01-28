package com.imuaythai.mtjudges.settings.service

import androidx.lifecycle.LiveData
import com.imuaythai.mtjudges.settings.model.SettingType
import com.imuaythai.mtjudges.settings.model.SettingsItem

interface SettingsService {

    fun provideSettingsItem(settingType: SettingType): LiveData<SettingsItem>

    fun saveSettingsItem(settingsItem: SettingsItem)

}