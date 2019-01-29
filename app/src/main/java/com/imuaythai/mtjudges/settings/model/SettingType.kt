package com.imuaythai.mtjudges.settings.model

import android.content.Context
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.common.model.Validator
import com.imuaythai.mtjudges.common.model.ValidatorFactory
import com.imuaythai.mtjudges.ui.validation.UrlValidator

enum class SettingType constructor(
    var settingName: Int,
    var defaultValue: Int
) : ValidatorFactory{

    API_HOST(
        R.string.settings_item_title_api_host,
        R.string.api_default_host
    ) {
        override fun create(context: Context): Validator<String> = UrlValidator(context)
    }

}