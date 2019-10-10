package com.imuaythai.mtjudges.time.judge.usecase

import com.imuaythai.mtjudges.common.model.UseCase
import com.imuaythai.mtjudges.service.MTService
import javax.inject.Inject

class StopFightUseCase @Inject constructor(
    private val mtService: MTService
): UseCase<StopFightUseCase.Request, StopFightUseCase.Response> {

    class Request{}

    class Response{}

    override fun execute(request: Request): Response {
        return Response()
    }
}