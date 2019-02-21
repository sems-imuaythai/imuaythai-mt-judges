package com.imuaythai.mtjudges.provider.webservice

import com.imuaythai.mtjudges.provider.webservice.dto.LoginResponse
import io.reactivex.Observable
import retrofit2.http.*

interface WebService {

    @POST("/api/account/login")
    @Headers("Content-Type: application/json")
    fun login(@Body params: HashMap<String, String>): Observable<LoginResponse>

}