package com.imuaythai.mtjudges.draw.card.injection

import androidx.lifecycle.ViewModel
import com.imuaythai.mtjudges.application.injection.view.model.ViewModelKey
import com.imuaythai.mtjudges.draw.card.DrawCardViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module class DrawCardModule {

    @Provides
    @IntoMap
    @ViewModelKey(DrawCardViewModel::class)
    fun provideDrawCardViewModel(viewModel: DrawCardViewModel): ViewModel = viewModel;

}