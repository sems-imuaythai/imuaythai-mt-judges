package com.imuaythai.mtjudges.navigation

import android.content.Context
import android.content.Intent
import com.imuaythai.mtjudges.application.navigation.ActivityNavigationAction
import com.imuaythai.mtjudges.main.judge.MainJudgeActivity

class NavigateToJuryJudgeActivityAction constructor() : ActivityNavigationAction(closeParent = false) {

    override fun createIntent(context: Context): Intent = Intent(context,MainJudgeActivity::class.java)
}