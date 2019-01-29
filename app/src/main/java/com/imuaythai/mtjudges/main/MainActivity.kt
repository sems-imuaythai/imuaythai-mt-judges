package com.imuaythai.mtjudges.main

import androidx.lifecycle.ViewModelProvider
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.common.BaseActivity

class MainActivity : BaseActivity<MainViewModel>() {

    override fun provideViewLayout(): Int = R.layout.main_activity

    override fun provideFragmentContainer() : Int = R.id.container

    override fun onInjectComponent(component: ApplicationComponent) {
        component.inject(this)
    }

    override fun provideViewModel(provider: ViewModelProvider): MainViewModel = provider.get(MainViewModel::class.java)

    override fun onBindView(viewModel: MainViewModel) { }

}