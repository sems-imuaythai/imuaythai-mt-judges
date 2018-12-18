package com.imuaythai.mtjudges.application.navigation

import android.content.Context
import android.content.Intent

abstract class ActivityNavigationAction constructor(
    var closeParent : Boolean = false
) : NavigateAction{

    abstract fun createIntent(context : Context) : Intent

    override fun visit(handler: NavigationHandler) {
        handler.navigate(this)
    }

}