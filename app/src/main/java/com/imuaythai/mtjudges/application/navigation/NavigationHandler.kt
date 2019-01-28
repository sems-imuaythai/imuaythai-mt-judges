package com.imuaythai.mtjudges.application.navigation

interface NavigationHandler {

    fun navigate(action : ActivityNavigationAction)

    fun navigate(action : FragmentNavigateAction)

    fun navigate(action: FinishActivityAction)

}