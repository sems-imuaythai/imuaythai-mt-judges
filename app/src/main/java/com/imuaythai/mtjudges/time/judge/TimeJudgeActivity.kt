package com.imuaythai.mtjudges.time.judge

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.common.BaseActivity
import com.imuaythai.mtjudges.databinding.TimeJudgeFragmentBinding
import com.imuaythai.mtjudges.time.judge.injection.TimeJudgeModule

class TimeJudgeActivity : BaseActivity<TimeJudgeViewModel>() {

    lateinit var binding: TimeJudgeFragmentBinding

    override fun provideViewLayout(): Int = R.layout.time_judge_fragment

    override fun onInjectComponent(component: ApplicationComponent) = component.plus(TimeJudgeModule()).inject(this)

    override fun onInitializeDataBinding(int: Int) {
        binding = DataBindingUtil.setContentView(this, provideViewLayout())
    }

    override fun provideViewModel(provider: ViewModelProvider): TimeJudgeViewModel = provider.get(TimeJudgeViewModel::class.java)

    override fun onBindView(viewModel: TimeJudgeViewModel) {
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
    }

}