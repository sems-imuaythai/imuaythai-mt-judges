package com.imuaythai.mtjudges.change.settings

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.imuaythai.mtjudges.application.navigation.FinishActivityAction
import com.imuaythai.mtjudges.change.settings.model.SettingsListItem
import com.imuaythai.mtjudges.common.BaseViewModel
import com.imuaythai.mtjudges.settings.model.SettingType
import com.imuaythai.mtjudges.settings.model.SettingsItem
import com.imuaythai.mtjudges.settings.service.SettingsService
import java.io.Serializable
import javax.inject.Inject

class ChangeSettingsViewModel @Inject constructor(
    val settingsService : SettingsService,
    val settingType: SettingType,
    val context: Context
) : BaseViewModel(){

    val settingsItem : LiveData<SettingsItem> = settingsService.provideSettingsItem(settingType);

    val settingValueError : MutableLiveData<String> = MutableLiveData()

    fun saveSettingValue(text: String){
        if(text.isEmpty()){
            requestSaveSetting(settingsItem.value!!.edit(context.getString(settingType.defaultValue)))
        }else{
            settingValueError.value = settingType.createValidator(context).validate(text)
            if(settingValueError.value == null) {
                requestSaveSetting(settingsItem.value!!.edit(text))
            }
        }
    }

    private fun requestSaveSetting(settingsItem: SettingsItem){
        settingsService.saveSettingsItem(settingsItem)
        navigate(FinishActivityAction())
    }

    fun saveSettingOption(value: String) = requestSaveSetting(settingsItem.value!!.edit(value))

}