package com.imuaythai.mtjudges.application.navigation

import androidx.fragment.app.Fragment

abstract class FragmentNavigateAction : NavigateAction{

    abstract fun createFragment() : Fragment

    override fun visit(handler: NavigationHandler) {
        handler.navigate(this)
    }
}