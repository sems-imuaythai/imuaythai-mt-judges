package com.imuaythai.mtjudges.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.imuaythai.mtjudges.application.navigation.ActivityNavigationAction
import com.imuaythai.mtjudges.application.navigation.FragmentNavigateAction
import com.imuaythai.mtjudges.time.judge.TimeJudgeActivity

class NavigateToTimeJudgeActivityAction constructor() : ActivityNavigationAction(closeParent = false) {

    override fun createIntent(context: Context): Intent = Intent(context,TimeJudgeActivity::class.java)
}