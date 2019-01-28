package com.imuaythai.mtjudges.settings.service

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.settings.model.SettingType
import com.imuaythai.mtjudges.settings.model.SettingsItem
import java.util.function.Predicate
import javax.inject.Inject
import javax.inject.Singleton

private var SETTINGS_PREFIX : String = "com.imuaythai.mtjudges.";

@Singleton
class SettingsServiceImpl @Inject constructor(
    private var context : Context,
    private var sharedPreferences : SharedPreferences
): SettingsService {

    private var configurationChanges : MutableLiveData<Int> = MutableLiveData()

    private var settingItems : List<MutableLiveData<SettingsItem>> = ArrayList()

    init {
        configurationChanges.value = 1;
        SettingType.values().forEach {
            settingItems += loadStringSettings(it)
        }
    }

    override fun provideSettingsItem(settingType: SettingType): LiveData<SettingsItem> {
       return settingItems.find { mutableLiveData -> mutableLiveData.value!!.settingType == settingType } as LiveData<SettingsItem>
    }

    override fun saveSettingsItem(settingsItem: SettingsItem) {
        val liveData : MutableLiveData<SettingsItem>? = settingItems
            .find { mutableLiveData -> mutableLiveData.value != null && mutableLiveData.value!!.settingType == settingsItem.settingType }

        if(liveData == null || liveData.value!!.value != settingsItem.value) {
            sharedPreferences.edit().putString(
                SETTINGS_PREFIX + settingsItem.settingType.name,
                settingsItem.value
            ).apply()
            configurationChanges.value = configurationChanges.value!!.plus(1);
            liveData!!.value = settingsItem;
        }
    }

    private fun loadStringSettings(settingType: SettingType) : MutableLiveData<SettingsItem> {
        val liveData : MutableLiveData<SettingsItem>  = MutableLiveData()
        liveData.value = SettingsItem(
            settingType,
            context.getString(settingType.settingName),
            sharedPreferences.getString(SETTINGS_PREFIX + settingType.name, context.getString(settingType.defaultValue))
                ?: context.getString(settingType.defaultValue)

        )
        return liveData;
    }

    override fun provideConfigurationChangeObservable(): LiveData<Int> = configurationChanges;

}