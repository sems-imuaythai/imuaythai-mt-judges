package com.imuaythai.mtjudges.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.imuaythai.mtjudges.application.navigation.RestartApplicationAction
import com.imuaythai.mtjudges.common.BaseViewModel
import com.imuaythai.mtjudges.login.exception.LoginErrorResolver
import com.imuaythai.mtjudges.login.model.LoginData
import com.imuaythai.mtjudges.login.service.LoginService
import com.imuaythai.mtjudges.login.validation.LoginFormValidator
import com.imuaythai.mtjudges.navigation.NavigateToPointJudgeActivityAction
import com.imuaythai.mtjudges.navigation.NavigateToSettingsActivityAction
import com.imuaythai.mtjudges.navigation.NavigateToTimeJudgeActivityAction
import com.imuaythai.mtjudges.settings.service.SettingsService
import io.reactivex.functions.Consumer
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginService: LoginService,
    private val settingsService : SettingsService,
    private val loginErrorResolver: LoginErrorResolver,
    private val loginFormValidator: LoginFormValidator
) : BaseViewModel() {

    private var configurationChangeId = 0

    val configurationChange : LiveData<Int> = settingsService.provideConfigurationChangeObservable()

    val loginError : MutableLiveData<String> = MutableLiveData()

    val passwordError : MutableLiveData<String> = MutableLiveData()

    init {
        loginFormValidator.passwordError = passwordError
        loginFormValidator.loginError = loginError
    }

    fun onLoginButtonClicked(email: String, password: String) {

        val loginData = LoginData(email,password)
        loginFormValidator.validate(loginData)
        if(loginFormValidator.isValid) {
            execute(loginService.login(loginData), Consumer {
                navigate(NavigateToPointJudgeActivityAction())
            }, loginErrorResolver)
        }
    }

    fun onSettingsButtonClicked() = navigate(NavigateToSettingsActivityAction())

    fun onConfigurationChange(value: Int) {
        if(configurationChangeId != 0 && configurationChangeId != value){
            navigate(RestartApplicationAction())
        }else{
            configurationChangeId = value
        }
    }

}
