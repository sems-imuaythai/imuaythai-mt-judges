package com.imuaythai.mtjudges.application.navigation

import android.content.Context
import android.content.Intent
import com.imuaythai.mtjudges.splash.SplashActivity


class RestartApplicationAction : ActivityNavigationAction(closeParent = true){

    override fun createIntent(context: Context): Intent = Intent(context, SplashActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
    }

    override fun visit(handler: NavigationHandler) {
        handler.navigate(this)
    }

}