package com.imuaythai.mtjudges.navigation

import android.content.Context
import android.content.Intent
import com.imuaythai.mtjudges.application.MTJudgesApplication
import com.imuaythai.mtjudges.application.navigation.ActivityNavigationAction
import com.imuaythai.mtjudges.connect.HubConnectActivity
import com.imuaythai.mtjudges.provider.hubservice.dto.Ring

class NavigateToConnectActivityAction constructor(
    var ring: Ring
) : ActivityNavigationAction(closeParent = true) {

    override fun createIntent(context: Context): Intent {
        MTJudgesApplication.get(context).createHubConnectionComponent(ring)
        return Intent(context, HubConnectActivity::class.java)
    }
}