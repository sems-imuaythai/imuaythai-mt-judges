package com.imuaythai.mtjudges.splash

import androidx.lifecycle.ViewModelProvider
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.common.BaseActivity

class SplashActivity : BaseActivity<SplashViewModel>() {

    override fun provideViewLayout(): Int = R.layout.splash_activity

    override fun onInjectComponent(component: ApplicationComponent) {
        component.inject(this)
    }

    override fun provideViewModel(provider: ViewModelProvider): SplashViewModel = provider.get(SplashViewModel::class.java)

    override fun onBindView(viewModel: SplashViewModel) { }
}