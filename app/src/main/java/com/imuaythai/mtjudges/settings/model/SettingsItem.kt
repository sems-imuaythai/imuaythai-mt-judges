package com.imuaythai.mtjudges.settings.model

class SettingsItem constructor(
    val settingType : SettingType,
    val name : String,
    val value : String
){

    fun edit(value: String) = SettingsItem(settingType,name,value)

}