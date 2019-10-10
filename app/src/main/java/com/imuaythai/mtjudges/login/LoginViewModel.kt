package com.imuaythai.mtjudges.login

import androidx.lifecycle.MutableLiveData
import com.imuaythai.mtjudges.common.BaseViewModel
import com.imuaythai.mtjudges.common.model.Resource
import com.imuaythai.mtjudges.common.view.ConfigurationChangeObserver
import com.imuaythai.mtjudges.login.usecase.InitializeUseCase
import com.imuaythai.mtjudges.login.usecase.LoginUseCase
import com.imuaythai.mtjudges.navigation.NavigateToJuryJudgeActivityAction
import com.imuaythai.mtjudges.navigation.NavigateToPointJudgeActivityAction
import com.imuaythai.mtjudges.navigation.NavigateToSettingsActivityAction
import com.imuaythai.mtjudges.navigation.NavigateToTimeJudgeActivityAction
import com.imuaythai.mtjudges.provider.dto.FightDataDto
import com.imuaythai.mtjudges.provider.dto.UserRole
import com.imuaythai.mtjudges.settings.service.SettingsService
import javax.inject.Inject
import javax.inject.Named

class LoginViewModel @Inject constructor(
    @Named("SELECTED_RING") val ringName: String,
    private val settingsService : SettingsService,
    private val initializeLoginUseCase: InitializeUseCase,
    private val loginUseCase: LoginUseCase
): BaseViewModel() {

    private val configurationChangeObserver = ConfigurationChangeObserver(this)

    val ringType: String = ringName.toUpperCase()

    val isScreenLoading : MutableLiveData<Boolean> = MutableLiveData()

    val pinLoginError: MutableLiveData<String> = MutableLiveData()

    val fightDataResource: MutableLiveData<Resource<FightDataDto>> = MutableLiveData()

    init {
        configurationChangeObserver.observe(settingsService.provideConfigurationChangeObservable())
        initializeRequest()
    }

    fun initializeRequest(){
        execute(initializeLoginUseCase,InitializeUseCase.Request())
            .bind(fightDataResource)
    }

    fun pinLoginRequest(pinCode: String){
        val request = LoginUseCase.Request(pinCode)
        execute(loginUseCase,request).withLoader(isScreenLoading).subscribe({ response ->
            when(response.userRole){
                UserRole.TIME_JUDGE -> navigate(NavigateToTimeJudgeActivityAction())
                UserRole.POINT_JUDGE -> navigate(NavigateToPointJudgeActivityAction())
                UserRole.MAIN_JUDGE -> navigate(NavigateToJuryJudgeActivityAction())
            }
        },{ error ->
            pinLoginError.value = error.message
        })
    }

    fun onSettingsButtonClicked() = navigate(NavigateToSettingsActivityAction())

}
