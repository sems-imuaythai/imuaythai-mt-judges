package com.imuaythai.mtjudges.provider.webservice

import com.imuaythai.mtjudges.provider.dto.AccessTokenDto
import com.imuaythai.mtjudges.provider.dto.FightDataDto
import com.imuaythai.mtjudges.provider.dto.PinDto
import retrofit2.Call
import retrofit2.http.*

interface WebService{

    @GET(value = "/api/Contests/{contestId}/Rings/{ringName}/fight")
    @Headers(value = ["No-Authentication: true"])
    fun initialize(@Path("contestId") contestId: Int,
                   @Path("ringName") ringName: String): Call<FightDataDto>

    @POST(value = "/api/Contests/{contestId}/Rings/{ringName}/tasks/authorize")
    @Headers(value = ["No-Authentication: true"])
    fun login(@Path("contestId") contestId: Int,
              @Path("ringName") ringName: String,
              @Body pin: PinDto
    ): Call<AccessTokenDto>

}