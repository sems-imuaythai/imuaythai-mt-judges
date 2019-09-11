package com.imuaythai.mtjudges.provider

import com.imuaythai.mtjudges.common.exception.RequestException
import com.imuaythai.mtjudges.provider.authorization.AccessTokenDecoder
import com.imuaythai.mtjudges.provider.authorization.AuthorizationRepository
import com.imuaythai.mtjudges.provider.dto.*
import com.imuaythai.mtjudges.provider.webservice.WebService
import com.imuaythai.mtjudges.service.*
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

class MTWebService @Inject constructor(
    @Named("SELECTED_RING") val ringName: String,
    private val webService: WebService,
    private val authorizationRepository: AuthorizationRepository,
    private val accessTokenDecoder: AccessTokenDecoder
): MTService {

    override fun contest(): ContestDto {

        val contest = authorizationRepository.getContest()

        return if(contest != null){
            contest
        } else {
            val response = webService.contests().execute()
            read(response){ contestListDto->
                val contestDto = contestListDto.contests!![0]
                authorizationRepository.setContest(contestDto)
                contestDto;
            }
        }
    }

    override fun fight(): FightDataDto {

        val contestId = contest().id

        val response = webService.fight(contestId, ringName).execute()
        return read(response){ fightDto ->
            fightDto
        }
    }

    override fun login(pin: String): UserDataDto {

        val contestId = contest().id

        val response = webService.authorize(contestId, ringName, PinDto(pin)).execute()
        return read(response){ accessToken ->
            authorizationRepository.setAuthToken(accessToken.accessToken)
            accessTokenDecoder.decodeUserData(accessToken)
        }
    }

    override fun sendRoundPoints(points: PointCardDto) {

    }

    override fun sendKnockOutResult(knockOutDto: KnockOutDto) {

    }

    override fun commitResult() {

    }

    override fun logout() {
        authorizationRepository.clearSession()
    }

    private fun <T,R> read(response: Response<T?>, callable: (T) -> R ): R{
        if(response.isSuccessful && response.body() != null){
            return callable.invoke(response.body()!!)
        } else {
            throw RequestException()
        }
    }
}