package com.imuaythai.mtjudges.time.judge.usecase

import com.imuaythai.mtjudges.common.model.UseCase
import com.imuaythai.mtjudges.service.MTService
import javax.inject.Inject

class ResumeFightUseCase @Inject constructor(
    private val mtService: MTService
): UseCase<ResumeFightUseCase.Request, ResumeFightUseCase.Response> {

    class Request{}

    class Response{}

    override fun execute(request: Request): Response {
        mtService.resumeFight()
        return Response()
    }
}