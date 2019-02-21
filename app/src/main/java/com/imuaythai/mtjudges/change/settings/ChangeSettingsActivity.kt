package com.imuaythai.mtjudges.change.settings

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.change.settings.injection.ChangeSettingsModule
import com.imuaythai.mtjudges.change.settings.model.SettingsListItem
import com.imuaythai.mtjudges.change.settings.view.SettingOptionAdapter
import com.imuaythai.mtjudges.common.BaseActivity
import com.imuaythai.mtjudges.settings.model.SettingInputType
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

        when(viewModel.settingType.inputType){

            SettingInputType.TEXT -> {
                text_input.visibility = View.VISIBLE
                list_view_input.visibility = View.GONE
                list_view_input.requestFocus()
                submit_button.visibility = View.VISIBLE
                submit_button.setOnClickListener { viewModel.saveSettingValue(text_input.text.toString()) }

                viewModel.settingValueError.observe(this, Observer { text_input.error = it })
                viewModel.settingsItem.observe(this, Observer { item ->
                    toolbar.title = item.name
                    text_input.setText(item.value)
                    text_input.setHint(item.settingType.defaultValue)
                })

            }

            SettingInputType.LIST -> {
                text_input.visibility = View.GONE
                list_view_input.visibility = View.VISIBLE
                submit_button.visibility = View.GONE
                list_view_input.layoutManager = LinearLayoutManager(this)
                val settingOptionAdapter = SettingOptionAdapter(
                    viewModel.settingType.createOptions(this))
                { item : SettingsListItem -> viewModel.saveSettingOption(item.value) }
                viewModel.settingsItem.observe(this, Observer { item ->
                    settingOptionAdapter.setSelectedValue(item!!.value)
                })
                list_view_input.adapter = settingOptionAdapter
            }
        }
    }
}