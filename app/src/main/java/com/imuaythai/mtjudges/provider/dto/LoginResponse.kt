package com.imuaythai.mtjudges.provider.dto

class LoginResponse {

    var authToken : String = ""

    var rememberMe : Boolean = false

    var qrCode : String = ""

    var user : UserData? = null

}