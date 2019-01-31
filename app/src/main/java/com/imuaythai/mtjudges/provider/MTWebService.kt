package com.imuaythai.mtjudges.provider

import com.imuaythai.mtjudges.provider.dto.LoginResponse
import io.reactivex.Observable
import retrofit2.http.*

interface MTWebService {

    @POST("/api/account/login")
    @Headers("Content-Type: application/json")
    fun login(@Body params: HashMap<String, String>): Observable<LoginResponse>

}