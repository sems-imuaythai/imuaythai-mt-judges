package com.imuaythai.mtjudges.jury.judge.injection

import androidx.lifecycle.ViewModel
import com.imuaythai.mtjudges.application.injection.view.model.ViewModelKey
import com.imuaythai.mtjudges.jury.judge.JuryJudgeViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module class JuryJudgeModule {

    @Provides
    @IntoMap
    @ViewModelKey(JuryJudgeViewModel::class)
    fun provideJuryJudgeViewModel(viewModel: JuryJudgeViewModel): ViewModel = viewModel;

}