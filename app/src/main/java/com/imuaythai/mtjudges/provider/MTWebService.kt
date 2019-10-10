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
    private val fightDataStore: FightDataStore,
    private val fightRepository: FightRepository
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

        val fightData = fightRepository.getFightData()

        return if(fightData != null){
            fightData
        } else {
            val response = webService.fight(contest().id, ringName).execute()
            read(response){ fightDto ->
                fightDataStore.saveFight(fightDto)
                fightDto
            }
        }
    }

    override fun login(pin: String): UserDataDto {

        val response = webService.authorize(contest().id, ringName, PinDto(pin)).execute()

        return read(response){ accessToken ->
            authorizationRepository.setAuthToken(accessToken.accessToken)
            val userDataDto = accessTokenDecoder.decodeUserData(accessToken)
            fightDataStore.saveUserData(userDataDto)
            userDataDto
        }
    }

    override fun sendRoundPoints(points: AddRingFightPointsDto) {
        readSuccess(webService.sendPoints(
            contest().id, ringName, fight().fightId, 1, points
        ).execute())
    }

    override fun startRound() {
        readSuccess(webService.startRound(
            contest().id, ringName, fight().fightId, 1
        ).execute())
    }

    override fun pauseFight() {
        readSuccess(webService.pauseFight(
            contest().id, ringName, fight().fightId, 1
        ).execute())
    }

    override fun resumeFight() {
        readSuccess(webService.resumeFight(
            contest().id, ringName, fight().fightId, 1
        ).execute())
    }

    override fun resetFight() {
        readSuccess(webService.resetFight(
            contest().id, ringName, fight().fightId
        ).execute())
    }

    override fun sendKnockOutResult(knockOutDto: KnockOutDto) {

    }

    override fun acceptResult() {
        readSuccess(webService.acceptResults(
            contest().id, ringName, fight().fightId, 1
        ).execute())
    }

    override fun logout() {
        authorizationRepository.clearSession()
    }

    private fun <T> readSuccess(response: Response<T?>) {
        if(response.isSuccessful){
            /* do nothing */
        } else if (response.code() == 401){
            throw SessionExpiredException()
        } else {
            throw RequestException()
        }
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