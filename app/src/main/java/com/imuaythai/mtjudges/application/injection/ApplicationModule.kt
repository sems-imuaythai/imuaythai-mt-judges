package com.imuaythai.mtjudges.application.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imuaythai.mtjudges.application.injection.view.model.ViewModelFactory
import com.imuaythai.mtjudges.application.injection.view.model.ViewModelKey
import com.imuaythai.mtjudges.application.injection.view.model.EmptyViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ApplicationModule {

    @Provides
    fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory = factory;

    @Provides @IntoMap
    @ViewModelKey(EmptyViewModel::class)
    fun provideViewModel(): ViewModel = EmptyViewModel();

}