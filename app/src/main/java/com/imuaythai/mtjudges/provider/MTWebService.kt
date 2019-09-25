package com.imuaythai.mtjudges.provider

import com.imuaythai.mtjudges.common.exception.RequestException
import com.imuaythai.mtjudges.common.exception.SessionExpiredException
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
    private val accessTokenDecoder: AccessTokenDecoder,
    private val fightDataStore: FightDataStore
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

        val response = webService.fight(contest().id, ringName).execute()

        return read(response){ fightDto ->
            fightDataStore.saveFight(fightDto)
            fightDto
        }
    }

    override fun login(pin: String): UserDataDto {

        val response = webService.authorize(contest().id, ringName, PinDto(pin)).execute()
        fightDataStore.updateFightStatus(FightStatusDto(1,FightState.STARTED,3600000))//TODO MOCK

        return read(response){ accessToken ->
            authorizationRepository.setAuthToken(accessToken.accessToken)
            val userDataDto = accessTokenDecoder.decodeUserData(accessToken)
            fightDataStore.saveUserData(userDataDto)
            userDataDto
        }
    }

    override fun sendRoundPoints(points: AddRingFightPointsDto) {
        val response = webService.sendPoints(contest().id, ringName, points).execute()
        return read(response){ fightDataDto -> }
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
        } else if (response.code() == 401){
            throw SessionExpiredException()
        } else {
            throw RequestException()
        }
    }
}