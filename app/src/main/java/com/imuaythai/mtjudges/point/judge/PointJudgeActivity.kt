package com.imuaythai.mtjudges.point.judge

import androidx.lifecycle.ViewModelProvider
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.common.BaseFragment
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.common.BaseActivity
import com.imuaythai.mtjudges.point.judge.injection.PointJudgeModule
import com.imuaythai.mtjudges.time.judge.injection.TimeJudgeModule

class PointJudgeActivity : BaseActivity<PointJudgeViewModel>() {

    override fun provideViewLayout(): Int = R.layout.score_card_fragment

    override fun onInjectComponent(component: ApplicationComponent) = component.plus(PointJudgeModule()).inject(this)

    override fun provideViewModel(provider: ViewModelProvider): PointJudgeViewModel = provider.get(PointJudgeViewModel::class.java)

    override fun onBindView(viewModel: PointJudgeViewModel) {

    }

}