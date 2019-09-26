package com.imuaythai.mtjudges.knockout.card.usecase

import com.imuaythai.mtjudges.common.model.UseCase
import com.imuaythai.mtjudges.provider.dto.AddRingFightPointsDto
import com.imuaythai.mtjudges.service.MTService
import javax.inject.Inject

class SendKnockoutTypeUseCase @Inject constructor(
    private val mtService: MTService
): UseCase<SendKnockoutTypeUseCase.Request, SendKnockoutTypeUseCase.Response> {

    data class Request(
        val addRingFightPointsDto: AddRingFightPointsDto
    )

    class Response{}

    override fun execute(request: Request): Response {
        mtService.sendRoundPoints(request.addRingFightPointsDto)
        return Response()
    }
}