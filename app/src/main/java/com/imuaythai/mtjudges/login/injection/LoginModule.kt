package com.imuaythai.mtjudges.login.injection

import androidx.lifecycle.ViewModel
import com.imuaythai.mtjudges.application.injection.view.model.ViewModelKey
import com.imuaythai.mtjudges.login.LoginViewModel
import com.imuaythai.mtjudges.login.service.LoginService
import com.imuaythai.mtjudges.login.service.LoginServiceImpl
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module class LoginModule {

    @Provides fun provideLoginService(service: LoginServiceImpl): LoginService = service

    @Provides @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun provideViewModel(viewModel: LoginViewModel): ViewModel = viewModel;
}