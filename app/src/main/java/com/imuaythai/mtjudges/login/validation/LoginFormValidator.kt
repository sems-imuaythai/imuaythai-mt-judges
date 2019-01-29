package com.imuaythai.mtjudges.login.validation

import androidx.lifecycle.MutableLiveData
import com.imuaythai.mtjudges.common.model.FormValidator
import com.imuaythai.mtjudges.login.model.LoginData
import com.imuaythai.mtjudges.ui.validation.EmailValidator
import com.imuaythai.mtjudges.ui.validation.PasswordValidator
import javax.inject.Inject

class LoginFormValidator @Inject constructor(
    val emailValidator: EmailValidator,
    val passwordValidator: PasswordValidator
) : FormValidator<LoginData>(){

    lateinit var loginError : MutableLiveData<String>

    lateinit var passwordError : MutableLiveData<String>

    override fun validate(data: LoginData) {
        loginError.value = emailValidator.validate(data.email)
        passwordError.value = passwordValidator.validate(data.password)
        isValid = loginError.value == null && passwordError.value == null;
    }
}