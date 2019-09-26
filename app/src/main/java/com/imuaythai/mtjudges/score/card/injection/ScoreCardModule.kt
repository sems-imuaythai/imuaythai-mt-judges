package com.imuaythai.mtjudges.score.card.injection

import androidx.lifecycle.ViewModel
import com.imuaythai.mtjudges.application.injection.view.model.ViewModelKey
import com.imuaythai.mtjudges.knockout.card.KnockoutCardViewModel
import com.imuaythai.mtjudges.score.card.ScoreCardViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module class ScoreCardModule {

    @Provides
    @IntoMap
    @ViewModelKey(ScoreCardViewModel::class)
    fun provideScoreCardViewModel(viewModel: ScoreCardViewModel): ViewModel = viewModel;

}