package com.imuaythai.mtjudges.knockout.card.injection

import androidx.lifecycle.ViewModel
import com.imuaythai.mtjudges.application.injection.view.model.ViewModelKey
import com.imuaythai.mtjudges.knockout.card.KnockoutCardViewModel
import com.imuaythai.mtjudges.knockout.card.model.KnockoutButtonLiveData
import com.imuaythai.mtjudges.knockout.card.model.RadioGroupBehavior
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module class KnockoutCardModule {

    @Provides
    fun provideRadioGroupBehavior(): RadioGroupBehavior<KnockoutButtonLiveData> = RadioGroupBehavior()

    @Provides
    @IntoMap
    @ViewModelKey(KnockoutCardViewModel::class)
    fun provideKnockoutCardViewModel(viewModel: KnockoutCardViewModel): ViewModel = viewModel;

}