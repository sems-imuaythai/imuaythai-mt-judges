package com.imuaythai.mtjudges.change.settings

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    var settingValueError : MutableLiveData<String> = MutableLiveData()

    fun changeSettingValue(text: String){
        if(text.isEmpty()){
            requestSaveSetting(settingsItem.value!!.edit(context.getString(settingType.defaultValue)))
        }else{
            settingValueError.value = settingType.create(context).validate(text)
            if(settingValueError.value == null) {
                requestSaveSetting(settingsItem.value!!.edit(text))
            }
        }
    }

    private fun requestSaveSetting(settingsItem: SettingsItem){
        settingsService.saveSettingsItem(settingsItem)
        navigate(FinishActivityAction())
    }
}