package com.imuaythai.mtjudges.provider

import com.imuaythai.mtjudges.common.exception.RequestException
import com.imuaythai.mtjudges.provider.authorization.AccessTokenDecoder
import com.imuaythai.mtjudges.provider.authorization.AuthorizationRepository
import com.imuaythai.mtjudges.provider.dto.*
import com.imuaythai.mtjudges.provider.webservice.WebService
import com.imuaythai.mtjudges.service.*
import javax.inject.Inject

class MTWebService @Inject constructor(
    private val webService: WebService,
    private val authorizationRepository: AuthorizationRepository,
    private val accessTokenDecoder: AccessTokenDecoder
): MTService {

    override fun initialize(ring: Ring): FightDataDto {

        val response = webService.initialize(1122, ring.name).execute()

        if(response.isSuccessful && response.body() != null){

            return response.body()!!

        } else {

            throw RequestException()

        }
    }

    override fun login(pin: String): UserDataDto {

        val response = webService.login(1122, Ring.A.name, PinDto(pin)).execute()

        if(response.isSuccessful && response.body() != null){

            val accessToken = response.body()!!

            authorizationRepository.setAuthToken(accessToken.accessToken)

            return accessTokenDecoder.decodeUserData(accessToken)

        } else {

            throw RequestException()

        }

    }

    override fun sendRoundPoints(points: PointCardDto) {

    }

    override fun sendKnockOutResult(knockOutDto: KnockOutDto) {

    }

    override fun commitResult() {

    }
}