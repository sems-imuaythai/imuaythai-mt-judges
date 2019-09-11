package com.imuaythai.mtjudges.login.injection

import androidx.lifecycle.ViewModel
import com.imuaythai.mtjudges.application.injection.view.model.ViewModelKey
import com.imuaythai.mtjudges.login.LoginViewModel
import com.imuaythai.mtjudges.provider.MTWebService
import com.imuaythai.mtjudges.service.MTService
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module class LoginModule {

    @Provides @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun provideViewModel(viewModel: LoginViewModel): ViewModel = viewModel;
}