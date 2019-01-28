package com.imuaythai.mtjudges.navigation

import android.content.Context
import android.content.Intent
import com.imuaythai.mtjudges.application.navigation.ActivityNavigationAction
import com.imuaythai.mtjudges.change.settings.ChangeSettingsActivity
import com.imuaythai.mtjudges.settings.model.SettingType

class NavigateToChangeSettingsActivityAction constructor(
    var settingType: SettingType
) : ActivityNavigationAction(closeParent = false) {

    override fun createIntent(context: Context): Intent {
        return Intent(context, ChangeSettingsActivity::class.java).apply {
            putExtra(ChangeSettingsActivity.ARG_SETTING_TYPE,settingType)
        }
    }
}