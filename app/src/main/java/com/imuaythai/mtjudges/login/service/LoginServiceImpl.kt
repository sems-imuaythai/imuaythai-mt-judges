package com.imuaythai.mtjudges.login.service

import com.imuaythai.mtjudges.login.model.LoginData
import com.imuaythai.mtjudges.provider.MTWebService
import com.imuaythai.mtjudges.provider.dto.LoginResponse
import io.reactivex.Observable
import javax.inject.Inject


class LoginServiceImpl @Inject constructor(
    var mtWebService: MTWebService
): LoginService {

    override fun login(loginData: LoginData) : Observable<LoginResponse> {
        return mtWebService.login(hashMapOf(
            "email" to loginData.email,
            "password" to loginData.password
        ))
    }

}