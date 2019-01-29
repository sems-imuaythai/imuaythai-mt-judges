package com.imuaythai.mtjudges.provider

import io.reactivex.Observable
import retrofit2.http.*

interface MTWebService {

    @POST("/api/account/login")
    @Headers("Content-Type: application/json")
    fun login(@Body params: HashMap<String, String>): Observable<String>

}