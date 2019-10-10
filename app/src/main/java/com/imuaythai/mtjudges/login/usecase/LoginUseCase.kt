package com.imuaythai.mtjudges.login.usecase

import com.imuaythai.mtjudges.common.model.UseCase
import com.imuaythai.mtjudges.service.MTService
import com.imuaythai.mtjudges.provider.dto.UserRole
import com.imuaythai.mtjudges.provider.hubservice.HubService
import com.imuaythai.mtjudges.service.FightRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val mtService: MTService,
    private val hubService: HubService,
    private val fightRepository: FightRepository
): UseCase<LoginUseCase.Request,LoginUseCase.Response>{

    data class Request(
        val pinCode: String
    )

    data class Response(
        val userName: String,
        val userRole: UserRole
    )

    override fun execute(request: Request): Response {

        val response = mtService.login("test1")

        Thread.sleep(250)

        hubService.connect()
        hubService.join(mtService.fight().fightId)

        return Response(
            response.familyName,
            UserRole.MAIN_JUDGE
        )
    }
}