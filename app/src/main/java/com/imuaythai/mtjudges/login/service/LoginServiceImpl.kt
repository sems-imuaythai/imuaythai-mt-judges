package com.imuaythai.mtjudges.login.service

import com.imuaythai.mtjudges.login.model.LoginData
import com.imuaythai.mtjudges.provider.webservice.WebService
import com.imuaythai.mtjudges.provider.webservice.dto.LoginResponse
import io.reactivex.Observable
import javax.inject.Inject


class LoginServiceImpl @Inject constructor(
    var mtWebService: WebService
): LoginService {

    override fun login(loginData: LoginData) : Observable<LoginResponse> {
        return mtWebService.login(hashMapOf(
            "email" to loginData.email,
            "password" to loginData.password
        ))
    }

}