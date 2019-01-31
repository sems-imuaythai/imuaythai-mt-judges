package com.imuaythai.mtjudges.test

import android.os.Handler
import android.os.Looper
import com.imuaythai.mtjudges.common.BaseViewModel
import com.imuaythai.mtjudges.navigation.*
import javax.inject.Inject

class TestViewModel @Inject constructor() : BaseViewModel() {

    fun clickedButton1() = navigate(NavigateToTimeJudgeActivityAction())

    fun clickedButton2() = navigate(NavigateToPointJudgeActivityAction())

    fun clickedButton3() = navigate(NavigateToJuryJudgeActivityAction())

}