package com.imuaythai.mtjudges.time.judge.injection

import androidx.lifecycle.ViewModel
import com.imuaythai.mtjudges.application.injection.view.model.ViewModelKey
import com.imuaythai.mtjudges.time.judge.TimeJudgeViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module class TimeJudgeModule {

    @Provides
    @IntoMap
    @ViewModelKey(TimeJudgeViewModel::class)
    fun provideTimeJudgeViewModel(viewModel: TimeJudgeViewModel): ViewModel = viewModel;

}