package com.imuaythai.mtjudges.jury.judge

import androidx.lifecycle.ViewModelProvider
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.common.BaseActivity
import com.imuaythai.mtjudges.jury.judge.injection.JuryJudgeModule
import com.imuaythai.mtjudges.point.judge.PointJudgeViewModel

class JuryJudgeActivity : BaseActivity<JuryJudgeViewModel>() {

    override fun provideViewLayout(): Int = R.layout.jury_panel_fragment

    override fun onInjectComponent(component: ApplicationComponent) = component.plus(JuryJudgeModule()).inject(this)

    override fun provideViewModel(provider: ViewModelProvider) = provider.get(JuryJudgeViewModel::class.java)

    override fun onBindView(viewModel: JuryJudgeViewModel) { }

}