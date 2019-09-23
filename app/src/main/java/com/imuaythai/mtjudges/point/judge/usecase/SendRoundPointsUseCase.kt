package com.imuaythai.mtjudges.point.judge.usecase

import com.imuaythai.mtjudges.common.model.UseCase
import com.imuaythai.mtjudges.provider.dto.UserRole
import com.imuaythai.mtjudges.service.MTService
import javax.inject.Inject

class SendRoundPointsUseCase @Inject constructor(
    private val mtService: MTService
): UseCase<SendRoundPointsUseCase.Request, SendRoundPointsUseCase.Response> {

    data class Request(
        val pinCode: String
    )

    data class Response(
        val userName: String,
        val userRole: UserRole
    )

    override fun execute(request: Request): Response {
        Thread.sleep(2000)
        //val response = mtService.sendRoundPoints("456321")
        return Response(
            "",
            UserRole.POINT_JUDGE
        )
    }
}