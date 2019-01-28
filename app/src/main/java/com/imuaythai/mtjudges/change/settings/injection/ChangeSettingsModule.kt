package com.imuaythai.mtjudges.change.settings.injection

import androidx.lifecycle.ViewModel
import com.imuaythai.mtjudges.application.injection.view.model.ViewModelKey
import com.imuaythai.mtjudges.change.settings.ChangeSettingsViewModel
import com.imuaythai.mtjudges.settings.model.SettingType
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module class ChangeSettingsModule constructor(
    var settingType: SettingType
){

    @Provides
    fun provideSettingType() : SettingType = settingType;

    @Provides
    @IntoMap
    @ViewModelKey(ChangeSettingsViewModel::class)
    fun provideChangeSettingsViewModel(viewModel: ChangeSettingsViewModel): ViewModel = viewModel;

}