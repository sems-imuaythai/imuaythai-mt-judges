package com.imuaythai.mtjudges.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.imuaythai.mtjudges.application.navigation.RestartApplicationAction
import com.imuaythai.mtjudges.common.BaseViewModel
import com.imuaythai.mtjudges.login.exception.LoginErrorResolver
import com.imuaythai.mtjudges.login.model.LoginData
import com.imuaythai.mtjudges.login.service.LoginService
import com.imuaythai.mtjudges.login.service.UserService
import com.imuaythai.mtjudges.login.validation.LoginFormValidator
import com.imuaythai.mtjudges.navigation.NavigateToConnectActivityAction
import com.imuaythai.mtjudges.navigation.NavigateToSettingsActivityAction
import com.imuaythai.mtjudges.navigation.NavigateToTestActivityAction
import com.imuaythai.mtjudges.provider.hubservice.dto.Ring
import com.imuaythai.mtjudges.provider.webservice.dto.UserData
import com.imuaythai.mtjudges.settings.model.SettingType
import com.imuaythai.mtjudges.settings.model.SettingsItem
import com.imuaythai.mtjudges.settings.service.SettingsService
import io.reactivex.functions.Consumer
import javax.inject.Inject


class LoginViewModel @Inject constructor(
    private val loginService: LoginService,
    private val settingsService : SettingsService,
    private val loginErrorResolver: LoginErrorResolver,
    private val loginFormValidator: LoginFormValidator,
    private val userService: UserService
) : BaseViewModel() {

    private var configurationChangeId = 0

    val configurationChange : LiveData<Int> = settingsService.provideConfigurationChangeObservable()

    val ringType : MutableLiveData<String> = MutableLiveData()

    val loginError : MutableLiveData<String> = MutableLiveData()

    val passwordError : MutableLiveData<String> = MutableLiveData()

    init {
        loginFormValidator.passwordError = passwordError
        loginFormValidator.loginError = loginError

        settingsService.provideSettingsItem(SettingType.SELECTED_RING).observeForever {
            ringType.value = it.value.capitalize()
        }
    }

    fun onLoginButtonClicked(email: String, password: String) {

        val loginData = LoginData(email,password)
        loginFormValidator.validate(loginData)
        if(loginFormValidator.isValid) {
            execute(loginService.login(loginData), Consumer {
                val userData : UserData? = it.user
                if(userData != null) {
                    userService.setUserData(userData)
                    val ring: Ring = Ring.valueOf(ringType.value ?: "A")
                    navigate(NavigateToConnectActivityAction(ring))
                }
            }, loginErrorResolver)
        }
    }

    fun onSettingsButtonClicked() = navigate(NavigateToSettingsActivityAction())

    fun onConfigurationChange(value: Int) {
        if (configurationChangeId != 0 && configurationChangeId != value) {
            navigate(RestartApplicationAction())
        } else {
            configurationChangeId = value
        }
    }

}
