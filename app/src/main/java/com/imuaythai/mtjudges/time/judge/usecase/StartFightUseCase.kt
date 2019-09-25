package com.imuaythai.mtjudges.time.judge.usecase

import com.imuaythai.mtjudges.common.model.UseCase
import com.imuaythai.mtjudges.provider.dto.AddRingFightPointsDto
import com.imuaythai.mtjudges.service.MTService
import javax.inject.Inject

class StartFightUseCase @Inject constructor(
    private val mtService: MTService
): UseCase<StartFightUseCase.Request, StartFightUseCase.Response> {

    class Request{}

    class Response{}

    override fun execute(request: Request): Response {
        return Response()
    }
}