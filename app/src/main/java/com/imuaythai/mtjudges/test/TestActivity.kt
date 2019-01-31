package com.imuaythai.mtjudges.test

import androidx.lifecycle.ViewModelProvider
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.common.BaseActivity
import kotlinx.android.synthetic.main.test_activity.*

class TestActivity : BaseActivity<TestViewModel>(){

    override fun provideViewLayout(): Int = R.layout.test_activity

    override fun onInjectComponent(component: ApplicationComponent) = component.inject(this)

    override fun provideViewModel(provider: ViewModelProvider): TestViewModel = provider.get(TestViewModel::class.java)

    override fun onBindView(viewModel: TestViewModel) {
        button_1.setOnClickListener{ viewModel.clickedButton1() }
        button_2.setOnClickListener{ viewModel.clickedButton2() }
        button_3.setOnClickListener{ viewModel.clickedButton3() }
    }
}