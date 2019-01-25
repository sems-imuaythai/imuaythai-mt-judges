package com.imuaythai.mtjudges.settings

import androidx.lifecycle.ViewModelProvider
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.common.BaseActivity
import com.imuaythai.mtjudges.settings.injection.SettingsModule

import kotlinx.android.synthetic.main.settings_activity.*

class SettingsActivity : BaseActivity<SettingsViewModel>() {

    override fun provideViewLayout(): Int = R.layout.settings_activity

    override fun onInjectComponent(component: ApplicationComponent) {
        component.plus(SettingsModule()).inject(this)
    }

    override fun provideViewModel(provider: ViewModelProvider): SettingsViewModel = provider.get(SettingsViewModel::class.java)

    override fun onBindView(viewModel: SettingsViewModel) {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener{ _ -> finish() }
    }

    override fun setArguments(viewModel: SettingsViewModel) { }
}