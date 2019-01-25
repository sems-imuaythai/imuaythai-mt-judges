package com.imuaythai.mtjudges.settings.injection

import androidx.lifecycle.ViewModel
import com.imuaythai.mtjudges.application.injection.view.model.ViewModelKey
import com.imuaythai.mtjudges.settings.SettingsViewModel
import com.imuaythai.mtjudges.time.judge.TimeJudgeViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module class SettingsModule {

    @Provides
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    fun provideTimeJudgeViewModel(viewModel: SettingsViewModel): ViewModel = viewModel;

}