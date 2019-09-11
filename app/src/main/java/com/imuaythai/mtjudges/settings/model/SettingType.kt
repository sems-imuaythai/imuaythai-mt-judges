package com.imuaythai.mtjudges.settings.model

import android.content.Context
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.change.settings.model.SettingsListItem
import com.imuaythai.mtjudges.common.model.Validator
import com.imuaythai.mtjudges.common.model.ValidatorFactory
import com.imuaythai.mtjudges.provider.dto.Ring
import com.imuaythai.mtjudges.ui.validation.SpinnerValidator
import com.imuaythai.mtjudges.ui.validation.UrlValidator

enum class SettingType constructor(
    var inputType: SettingInputType,
    var settingName: Int,
    var defaultValue: Int
) : ValidatorFactory,
    SettingOptionsFactory{

    API_HOST(
        SettingInputType.TEXT,
        R.string.settings_item_title_api_host,
        R.string.default_api_host
    ){
        override fun createValidator(context: Context): Validator<String> = UrlValidator(context)
    },

    SELECTED_RING(
        SettingInputType.LIST,
        R.string.settings_item_title_selected_ring,
        R.string.default_selected_ring
    ){
        override fun createValidator(context: Context): Validator<String> = SpinnerValidator(context)

        override fun createOptions(context: Context): List<SettingsListItem> = ArrayList<SettingsListItem>().apply{
            add(SettingsListItem(context.getString(R.string.settings_selected_ring_option_label,"A"),
                Ring.A.name))
            add(SettingsListItem(context.getString(R.string.settings_selected_ring_option_label,"B"),
                Ring.B.name))
            add(SettingsListItem(context.getString(R.string.settings_selected_ring_option_label,"C"),
                Ring.C.name))
        }
    }

}