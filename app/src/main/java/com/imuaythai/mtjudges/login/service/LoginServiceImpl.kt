package com.imuaythai.mtjudges.login.service

import javax.inject.Inject


class LoginServiceImpl @Inject constructor(): LoginService {

    override fun login(name: String, password: String) : String {
        if(name == "test" && password == "test"){
            return "Hello world!"
        }
        return "Error"
    }

}