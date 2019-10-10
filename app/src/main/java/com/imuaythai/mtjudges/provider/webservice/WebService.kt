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

    @POST(value = "/api/Contests/{contestId}/Rings/{ringName}/Fights/{fightId}/Rounds/{roundId}/Points")
    fun sendPoints( @Path("contestId") contestId: Int,
                    @Path("ringName") ringName: String,
                    @Path("fightId") fightId: Int,
                    @Path("roundId") roundId: Int,
                    @Body pointsDto: AddRingFightPointsDto
    ): Call<FightDataDto>

    @POST(value = "/api/Contests/{contestId}/Rings/{ringName}/Fights/{fightId}/Rounds/{roundId}/tasks/start")
    fun startRound( @Path("contestId") contestId: Int,
                    @Path("ringName") ringName: String,
                    @Path("fightId") fightId: Int,
                    @Path("roundId") roundId: Int
    ): Call<EmptyDto>

    @POST(value = "/api/Contests/{contestId}/Rings/{ringName}/Fights/{fightId}/Rounds/{roundId}/tasks/pause")
    fun pauseFight( @Path("contestId") contestId: Int,
                    @Path("ringName") ringName: String,
                    @Path("fightId") fightId: Int,
                    @Path("roundId") roundId: Int
    ): Call<EmptyDto>

    @POST(value = "/api/Contests/{contestId}/Rings/{ringName}/Fights/{fightId}/Rounds/{roundId}/tasks/resume")
    fun resumeFight( @Path("contestId") contestId: Int,
                     @Path("ringName") ringName: String,
                     @Path("fightId") fightId: Int,
                     @Path("roundId") roundId: Int
    ): Call<EmptyDto>

    @POST(value = "/api/Contests/{contestId}/Rings/{ringName}/Fights/{fightId}/Rounds/{roundId}/tasks/accept")
    fun acceptResults( @Path("contestId") contestId: Int,
                       @Path("ringName") ringName: String,
                       @Path("fightId") fightId: Int,
                       @Path("roundId") roundId: Int
    ): Call<EmptyDto>

    @POST(value = "/api/Contests/{contestId}/Rings/{ringName}/Fights/{fightId}/tasks/reset")
    fun resetFight( @Path("contestId") contestId: Int,
                       @Path("ringName") ringName: String,
                       @Path("fightId") fightId: Int
    ): Call<EmptyDto>

}