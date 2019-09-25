package com.imuaythai.mtjudges.main.judge

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.common.BaseActivity
import com.imuaythai.mtjudges.databinding.MainJudgeActivityBinding
import com.imuaythai.mtjudges.main.judge.injection.MainJudgeModule

class MainJudgeActivity : BaseActivity<MainJudgeViewModel>() {

    lateinit var binding: MainJudgeActivityBinding

    override fun provideViewLayout(): Int = R.layout.main_judge_activity

    override fun onInjectComponent(component: ApplicationComponent) = component.plus(MainJudgeModule()).inject(this)

    override fun onInitializeDataBinding(int: Int) {
        binding = DataBindingUtil.setContentView(this, provideViewLayout())
    }

    override fun provideViewModel(provider: ViewModelProvider) = provider.get(MainJudgeViewModel::class.java)

    override fun onBindView(viewModel: MainJudgeViewModel) {
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
    }

}