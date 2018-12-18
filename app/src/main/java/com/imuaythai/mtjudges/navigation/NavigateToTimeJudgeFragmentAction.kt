package com.imuaythai.mtjudges.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.imuaythai.mtjudges.application.navigation.FragmentNavigateAction
import com.imuaythai.mtjudges.time.judge.TimeJudgeFragment

class NavigateToTimeJudgeFragmentAction constructor() : FragmentNavigateAction() {

    override fun createFragment(): Fragment {
        val fragment = TimeJudgeFragment()
        val bundle = Bundle()
        fragment.arguments = bundle
        return fragment
    }

}