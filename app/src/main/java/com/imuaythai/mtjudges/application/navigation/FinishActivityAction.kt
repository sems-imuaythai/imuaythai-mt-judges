package com.imuaythai.mtjudges.application.navigation

import android.content.Context
import android.content.Intent

class FinishActivityAction : NavigateAction{

    override fun visit(handler: NavigationHandler) {
        handler.navigate(this)
    }

}