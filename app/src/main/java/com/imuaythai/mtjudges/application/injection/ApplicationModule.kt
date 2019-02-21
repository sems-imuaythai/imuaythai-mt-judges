package com.imuaythai.mtjudges.application.injection

import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imuaythai.mtjudges.application.injection.view.model.ViewModelFactory
import com.imuaythai.mtjudges.application.injection.view.model.ViewModelKey
import com.imuaythai.mtjudges.application.injection.view.model.EmptyViewModel
import com.imuaythai.mtjudges.login.service.UserService
import com.imuaythai.mtjudges.login.service.UserServiceImpl
import com.imuaythai.mtjudges.main.MainViewModel
import com.imuaythai.mtjudges.settings.model.SettingType
import com.imuaythai.mtjudges.settings.service.SettingsService
import com.imuaythai.mtjudges.settings.service.SettingsServiceImpl
import com.imuaythai.mtjudges.splash.SplashViewModel
import com.imuaythai.mtjudges.test.TestViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApplicationModule constructor(
    var context : Context
){

    @Provides
    fun provideApplicationContext() : Context = context;

    @Provides
    fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory = factory;

    @Provides @IntoMap
    @ViewModelKey(EmptyViewModel::class)
    fun provideEmptyViewModel(): ViewModel = EmptyViewModel();

    @Provides @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun provideMainViewModel(viewModel : MainViewModel): ViewModel = viewModel

    @Provides @IntoMap
    @ViewModelKey(TestViewModel::class)
    fun provideTestViewModel(viewModel : TestViewModel): ViewModel = viewModel

    @Provides @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun provideSplashViewModel(splashViewModel : SplashViewModel): ViewModel = splashViewModel

    @Provides @Singleton
    fun provideSharedPreferences(context: Context) : SharedPreferences = context.getSharedPreferences("com.imuaythai.mtjudges.preferences", 0);

    @Provides @Singleton
    fun provideSettingsService(service: SettingsServiceImpl) : SettingsService = service

    @Provides @Singleton
    fun provideUserService(service : UserServiceImpl) : UserService = service

    @Provides @Named("API_HOST")
    fun provideConfigurationApiHost(settingsService: SettingsService) : String
            = settingsService.provideSettingsItem(SettingType.API_HOST).value!!.value

    @Provides @Named("SELECTED_RING")
    fun provideConfigurationSelectedRing(settingsService: SettingsService) : String
            = settingsService.provideSettingsItem(SettingType.SELECTED_RING).value!!.value

}