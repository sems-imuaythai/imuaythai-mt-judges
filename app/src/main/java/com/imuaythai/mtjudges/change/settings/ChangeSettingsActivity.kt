package com.imuaythai.mtjudges.change.settings

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.change.settings.injection.ChangeSettingsModule
import com.imuaythai.mtjudges.common.BaseActivity
import com.imuaythai.mtjudges.settings.model.SettingType

import kotlinx.android.synthetic.main.settings_change_activity.*

class ChangeSettingsActivity : BaseActivity<ChangeSettingsViewModel>() {

    companion object {
        const val ARG_SETTING_TYPE : String = "SETTING_TYPE"
    }

    override fun provideViewLayout(): Int = R.layout.settings_change_activity

    override fun onInjectComponent(component: ApplicationComponent) {
        component.plus(ChangeSettingsModule(intent.getSerializableExtra((ARG_SETTING_TYPE))as SettingType)).inject(this)
    }

    override fun provideViewModel(provider: ViewModelProvider): ChangeSettingsViewModel = provider.get(ChangeSettingsViewModel::class.java)

    override fun onBindView(viewModel: ChangeSettingsViewModel) {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener{ _ -> finish() }

        viewModel.settingsItem.observe(this, Observer { item ->
            toolbar.title = item.name
            text_input.setText(item.value)
         })

        submit_button.setOnClickListener { viewModel.changeSettingValue(text_input.text.toString()) }
    }

    override fun setArguments(viewModel: ChangeSettingsViewModel) { }
}