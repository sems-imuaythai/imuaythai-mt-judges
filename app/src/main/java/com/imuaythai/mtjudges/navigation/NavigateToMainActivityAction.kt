package com.imuaythai.mtjudges.navigation

import android.content.Context
import android.content.Intent
import com.imuaythai.mtjudges.application.navigation.ActivityNavigationAction
import com.imuaythai.mtjudges.main.MainActivity

class NavigateToMainActivityAction : ActivityNavigationAction(closeParent = true) {

    override fun createIntent(context: Context): Intent {
        return Intent(context, MainActivity::class.java)
    }
}