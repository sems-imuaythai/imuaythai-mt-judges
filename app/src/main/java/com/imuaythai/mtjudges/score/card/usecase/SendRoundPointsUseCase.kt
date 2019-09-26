package com.imuaythai.mtjudges.score.card.usecase

import com.imuaythai.mtjudges.common.model.UseCase
import com.imuaythai.mtjudges.provider.dto.AddRingFightPointsDto
import com.imuaythai.mtjudges.service.MTService
import javax.inject.Inject

class SendRoundPointsUseCase @Inject constructor(
    private val mtService: MTService
): UseCase<SendRoundPointsUseCase.Request, SendRoundPointsUseCase.Response> {

    data class Request(
        val addRingFightPointsDto: AddRingFightPointsDto
    )

    class Response{}

    override fun execute(request: Request): Response {
        mtService.sendRoundPoints(request.addRingFightPointsDto)
        return Response()
    }
}