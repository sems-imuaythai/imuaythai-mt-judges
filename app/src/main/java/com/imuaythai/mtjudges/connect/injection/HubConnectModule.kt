package com.imuaythai.mtjudges.connect.injection

import androidx.lifecycle.ViewModel
import com.imuaythai.mtjudges.application.injection.view.model.ViewModelKey
import com.imuaythai.mtjudges.connect.HubConnectViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module class HubConnectModule {

    @Provides @IntoMap
    @ViewModelKey(HubConnectViewModel::class)
    fun provideViewModel(viewModel: HubConnectViewModel): ViewModel = viewModel;
}