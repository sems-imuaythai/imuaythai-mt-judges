package com.imuaythai.mtjudges.fight.header.injection

import androidx.lifecycle.ViewModel
import com.imuaythai.mtjudges.application.injection.view.model.ViewModelKey
import com.imuaythai.mtjudges.fight.header.FightHeaderViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module class FightHeaderModule {

    @Provides
    @IntoMap
    @ViewModelKey(FightHeaderViewModel::class)
    fun provideFightHeaderViewModel(viewModel: FightHeaderViewModel): ViewModel = viewModel;

}