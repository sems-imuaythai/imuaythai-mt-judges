package com.imuaythai.mtjudges.knockout.card.injection

import androidx.lifecycle.ViewModel
import com.imuaythai.mtjudges.application.injection.view.model.ViewModelKey
import com.imuaythai.mtjudges.knockout.card.KnockoutCardViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module class KnockoutCardModule {

    @Provides
    @IntoMap
    @ViewModelKey(KnockoutCardViewModel::class)
    fun provideKnockoutCardViewModel(viewModel: KnockoutCardViewModel): ViewModel = viewModel;

}