package com.imuaythai.mtjudges.draw.card.usecase

import com.imuaythai.mtjudges.common.model.UseCase
import com.imuaythai.mtjudges.provider.dto.DrawResultType
import com.imuaythai.mtjudges.provider.dto.FighterType
import com.imuaythai.mtjudges.service.MTService
import javax.inject.Inject

class SendDrawResultUseCase @Inject constructor(
    private val mtService: MTService
): UseCase<SendDrawResultUseCase.Request, SendDrawResultUseCase.Response> {

    data class Request(
        val drawResultType: DrawResultType,
        val fighterType: FighterType
    )

    class Response{}

    override fun execute(request: Request): Response {
        Thread.sleep(1500)
        return Response()
    }
}