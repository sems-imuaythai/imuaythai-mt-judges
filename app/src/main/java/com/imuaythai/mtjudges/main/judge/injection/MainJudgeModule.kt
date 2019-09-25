package com.imuaythai.mtjudges.main.judge.injection

import androidx.lifecycle.ViewModel
import com.imuaythai.mtjudges.application.injection.view.model.ViewModelKey
import com.imuaythai.mtjudges.main.judge.MainJudgeViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module class MainJudgeModule {

    @Provides
    @IntoMap
    @ViewModelKey(MainJudgeViewModel::class)
    fun provideJuryJudgeViewModel(viewModel: MainJudgeViewModel): ViewModel = viewModel;

}