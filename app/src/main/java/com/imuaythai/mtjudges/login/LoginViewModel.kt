package com.imuaythai.mtjudges.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.imuaythai.mtjudges.application.navigation.RestartApplicationAction
import com.imuaythai.mtjudges.common.BaseViewModel
import com.imuaythai.mtjudges.login.exception.LoginErrorResolver
import com.imuaythai.mtjudges.login.service.LoginService
import com.imuaythai.mtjudges.login.service.UserService
import com.imuaythai.mtjudges.login.validation.LoginFormValidator
import com.imuaythai.mtjudges.navigation.NavigateToSettingsActivityAction
import com.imuaythai.mtjudges.provider.hubservice.HubService
import com.imuaythai.mtjudges.provider.hubservice.dto.ConnectionState
import com.imuaythai.mtjudges.provider.hubservice.dto.Ring
import com.imuaythai.mtjudges.settings.model.SettingType
import com.imuaythai.mtjudges.settings.service.SettingsService
import javax.inject.Inject


class LoginViewModel @Inject constructor(
    private val loginService: LoginService,
    private val settingsService : SettingsService,
    private val loginErrorResolver: LoginErrorResolver,
    private val loginFormValidator: LoginFormValidator,
    private val userService: UserService,
    private val hubService: HubService
): BaseViewModel() {

    private var configurationChangeId = 0

    val hubConnectionState: LiveData<ConnectionState> = hubService.connect()

    val ringType : MutableLiveData<String> = MutableLiveData()

    val loginError : MutableLiveData<String> = MutableLiveData()

    val passwordError : MutableLiveData<String> = MutableLiveData()

    init {
        loginFormValidator.passwordError = passwordError
        loginFormValidator.loginError = loginError

        settingsService.provideSettingsItem(SettingType.SELECTED_RING).observeForever {
            ringType.value = it.value.capitalize()
        }

        settingsService.provideConfigurationChangeObservable().observeForever { value ->
            if (configurationChangeId != 0 && configurationChangeId != value) {
                navigate(RestartApplicationAction())
            } else {
                configurationChangeId = value
            }
        }
    }

    fun onLoginButtonClicked(email: String, password: String) {


        /*val loginData = LoginData(email,password)
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
        }*/
    }

    fun onSettingsButtonClicked() = navigate(NavigateToSettingsActivityAction())

    fun reconnect() = hubService.connect()

    fun onConfigurationChange(value: Int) {
        if (configurationChangeId != 0 && configurationChangeId != value) {
            navigate(RestartApplicationAction())
        } else {
            configurationChangeId = value
        }
    }

}
