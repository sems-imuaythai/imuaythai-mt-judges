package com.imuaythai.mtjudges.provider.webservice

import com.imuaythai.mtjudges.provider.dto.*
import retrofit2.Call
import retrofit2.http.*

interface WebService{

    @GET(value = "/api/Contests")
    @Headers(value = ["No-Authentication: true"])
    fun contests(): Call<ContestListDto>

    @GET(value = "/api/Contests/{contestId}/Rings/{ringName}/fight")
    @Headers(value = ["No-Authentication: true"])
    fun fight(@Path("contestId") contestId: Int,
                   @Path("ringName") ringName: String): Call<FightDataDto>

    @POST(value = "/api/Contests/{contestId}/Rings/{ringName}/tasks/authorize")
    @Headers(value = ["No-Authentication: true"])
    fun authorize(
                @Path("contestId") contestId: Int,
                @Path("ringName") ringName: String,
                @Body pin: PinDto
    ): Call<AccessTokenDto>

    @GET(value = "/api/Contests/{contestId}/Rings/{ringName}/fight/points")
    fun sendPoints( @Path("contestId") contestId: Int,
                    @Path("ringName") ringName: String,
                    @Body pointsDto: AddRingFightPointsDto
    ): Call<FightDataDto>

}