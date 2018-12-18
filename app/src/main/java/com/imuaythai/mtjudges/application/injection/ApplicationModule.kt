package com.imuaythai.mtjudges.application.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imuaythai.mtjudges.application.injection.view.model.ViewModelFactory
import com.imuaythai.mtjudges.application.injection.view.model.ViewModelKey
import com.imuaythai.mtjudges.application.injection.view.model.EmptyViewModel
import com.imuaythai.mtjudges.main.MainViewModel
import com.imuaythai.mtjudges.splash.SplashViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ApplicationModule {

    @Provides
    fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory = factory;

    @Provides @IntoMap
    @ViewModelKey(EmptyViewModel::class)
    fun provideEmptyViewModel(): ViewModel = EmptyViewModel();

    @Provides @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun provideMainViewModel(mainViewModel : MainViewModel): ViewModel = mainViewModel

    @Provides @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun provideSplashViewModel(splashViewModel : SplashViewModel): ViewModel = splashViewModel

}