package com.imuaythai.mtjudges.provider.webservice.dto

class LoginResponse {

    var authToken : String = ""

    var rememberMe : Boolean = false

    var qrCode : String = ""

    var user : UserData? = null

}