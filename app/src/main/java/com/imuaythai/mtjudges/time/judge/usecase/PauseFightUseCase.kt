package com.imuaythai.mtjudges.time.judge.usecase

import com.imuaythai.mtjudges.common.model.UseCase
import com.imuaythai.mtjudges.provider.dto.AddRingFightPointsDto
import com.imuaythai.mtjudges.service.MTService
import javax.inject.Inject

class PauseFightUseCase @Inject constructor(
    private val mtService: MTService
): UseCase<PauseFightUseCase.Request, PauseFightUseCase.Response> {

    class Request{}

    class Response{}

    override fun execute(request: Request): Response {
        return Response()
    }
}