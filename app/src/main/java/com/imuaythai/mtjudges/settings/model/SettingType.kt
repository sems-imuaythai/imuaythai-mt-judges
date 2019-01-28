package com.imuaythai.mtjudges.settings.model

import com.imuaythai.mtjudges.R

enum class SettingType constructor(
    var settingName: Int,
    var defaultValue: Int
){

    API_HOST(
        R.string.settings_item_title_api_host,
        R.string.api_default_host
    )

}