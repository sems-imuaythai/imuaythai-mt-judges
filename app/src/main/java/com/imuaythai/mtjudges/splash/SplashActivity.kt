package com.imuaythai.mtjudges.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.common.BaseActivity
import com.imuaythai.mtjudges.login.LoginFragment

import kotlinx.android.synthetic.main.splash_activity.*

class SplashActivity : BaseActivity<SplashViewModel>() {

    override fun provideViewLayout(): Int = R.layout.splash_activity

    override fun onInjectComponent(component: ApplicationComponent) {
        component.inject(this)
    }

    override fun provideViewModel(provider: ViewModelProvider): SplashViewModel = provider.get(SplashViewModel::class.java)

    override fun onBindView(viewModel: SplashViewModel) { }

    override fun setArguments(viewModel: SplashViewModel) { }
}