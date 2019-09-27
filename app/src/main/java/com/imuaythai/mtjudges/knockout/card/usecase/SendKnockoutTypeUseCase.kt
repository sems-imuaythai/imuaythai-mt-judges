package com.imuaythai.mtjudges.knockout.card.usecase

import com.imuaythai.mtjudges.common.model.UseCase
import com.imuaythai.mtjudges.provider.dto.FighterType
import com.imuaythai.mtjudges.provider.dto.KnockOutType
import com.imuaythai.mtjudges.service.MTService
import javax.inject.Inject

class SendKnockoutTypeUseCase @Inject constructor(
    private val mtService: MTService
): UseCase<SendKnockoutTypeUseCase.Request, SendKnockoutTypeUseCase.Response> {

    data class Request(
        val knockOutType: KnockOutType,
        val fighterType: FighterType
    )

    class Response{}

    override fun execute(request: Request): Response {
        Thread.sleep(1500)
        return Response()
    }
}