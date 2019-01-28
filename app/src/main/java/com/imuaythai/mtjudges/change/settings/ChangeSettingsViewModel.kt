package com.imuaythai.mtjudges.change.settings

import android.content.Context
import androidx.lifecycle.LiveData
import com.imuaythai.mtjudges.application.navigation.FinishActivityAction
import com.imuaythai.mtjudges.common.BaseViewModel
import com.imuaythai.mtjudges.settings.model.SettingType
import com.imuaythai.mtjudges.settings.model.SettingsItem
import com.imuaythai.mtjudges.settings.service.SettingsService
import javax.inject.Inject

class ChangeSettingsViewModel @Inject constructor(
    var settingsService : SettingsService,
    var settingType: SettingType,
    var context: Context
) : BaseViewModel(){

    var settingsItem : LiveData<SettingsItem> = settingsService.provideSettingsItem(settingType);

    fun changeSettingValue(text: String){
        if(text.isEmpty()){
            settingsService.saveSettingsItem(settingsItem.value!!.edit(context.getString(settingType.defaultValue)))
        }else{
            settingsService.saveSettingsItem(settingsItem.value!!.edit(text))
        }
        navigate(FinishActivityAction())
    }
}