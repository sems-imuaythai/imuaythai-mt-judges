package com.imuaythai.mtjudges.settings

import androidx.lifecycle.LiveData
import com.imuaythai.mtjudges.common.BaseViewModel
import com.imuaythai.mtjudges.navigation.NavigateToChangeSettingsActivityAction
import com.imuaythai.mtjudges.settings.model.SettingType
import com.imuaythai.mtjudges.settings.model.SettingsItem
import com.imuaythai.mtjudges.settings.service.SettingsService
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val settingsService : SettingsService
) : BaseViewModel(){

    var apiHostSettingsItem : LiveData<SettingsItem> = settingsService.provideSettingsItem(SettingType.API_HOST)

    var selectedRingSettingsItem : LiveData<SettingsItem> = settingsService.provideSettingsItem(SettingType.SELECTED_RING)

    fun clickedSettingsItem(settingType: SettingType) = navigate(NavigateToChangeSettingsActivityAction(settingType))

}