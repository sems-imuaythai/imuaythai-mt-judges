package com.imuaythai.mtjudges.point.judge

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.common.BaseActivity
import com.imuaythai.mtjudges.databinding.PointJudgeActivityBinding
import com.imuaythai.mtjudges.point.judge.injection.PointJudgeModule

class PointJudgeActivity : BaseActivity<PointJudgeViewModel>() {

    lateinit var binding: PointJudgeActivityBinding

    override fun provideViewLayout(): Int = R.layout.point_judge_activity

    override fun onInjectComponent(component: ApplicationComponent) = component.plus(PointJudgeModule()).inject(this)

    override fun provideViewModel(provider: ViewModelProvider): PointJudgeViewModel = provider.get(PointJudgeViewModel::class.java)

    override fun onInitializeDataBinding(int: Int) {
        binding = DataBindingUtil.setContentView(this, provideViewLayout())
    }

    override fun onBindView(viewModel: PointJudgeViewModel) {
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
    }

}