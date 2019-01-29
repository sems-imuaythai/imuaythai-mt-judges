package com.imuaythai.mtjudges.navigation

import android.content.Context
import android.content.Intent
import com.imuaythai.mtjudges.application.navigation.ActivityNavigationAction
import com.imuaythai.mtjudges.point.judge.PointJudgeActivity

class NavigateToPointJudgeActivityAction constructor() : ActivityNavigationAction(closeParent = true) {

    override fun createIntent(context: Context): Intent = Intent(context,PointJudgeActivity::class.java)
}