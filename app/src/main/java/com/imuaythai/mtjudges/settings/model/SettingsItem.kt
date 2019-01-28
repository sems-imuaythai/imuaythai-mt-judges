package com.imuaythai.mtjudges.settings.model

class SettingsItem constructor(
    var settingType : SettingType,
    var name : String,
    var value : String
){

    fun edit(value: String) = SettingsItem(settingType,name,value)

}