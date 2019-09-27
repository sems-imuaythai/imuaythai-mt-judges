package com.imuaythai.mtjudges.draw.card.injection

import androidx.lifecycle.ViewModel
import com.imuaythai.mtjudges.application.injection.view.model.ViewModelKey
import com.imuaythai.mtjudges.draw.card.DrawCardViewModel
import com.imuaythai.mtjudges.draw.card.model.DrawResultButtonLiveData
import com.imuaythai.mtjudges.draw.card.model.FighterTypeButtonLiveData
import com.imuaythai.mtjudges.knockout.card.model.RadioGroupBehavior
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module class DrawCardModule {

    @Provides
    fun provideDrawResultRadioGroupBehavior(): RadioGroupBehavior<DrawResultButtonLiveData> = RadioGroupBehavior()

    @Provides
    fun provideFighterTypeRadioGroupBehavior(): RadioGroupBehavior<FighterTypeButtonLiveData> = RadioGroupBehavior()

    @Provides
    @IntoMap
    @ViewModelKey(DrawCardViewModel::class)
    fun provideDrawCardViewModel(viewModel: DrawCardViewModel): ViewModel = viewModel;

}