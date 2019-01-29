package com.imuaythai.mtjudges.point.judge.injection

import androidx.lifecycle.ViewModel
import com.imuaythai.mtjudges.application.injection.view.model.ViewModelKey
import com.imuaythai.mtjudges.point.judge.PointJudgeViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module class PointJudgeModule {

    @Provides
    @IntoMap
    @ViewModelKey(PointJudgeViewModel::class)
    fun providePointJudgeViewModel(viewModel: PointJudgeViewModel): ViewModel = viewModel;

}