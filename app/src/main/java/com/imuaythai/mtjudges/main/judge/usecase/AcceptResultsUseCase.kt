package com.imuaythai.mtjudges.main.judge.usecase

import com.imuaythai.mtjudges.common.model.UseCase
import com.imuaythai.mtjudges.service.MTService
import javax.inject.Inject

class AcceptResultsUseCase @Inject constructor(
    private val mtService: MTService
): UseCase<AcceptResultsUseCase.Request, AcceptResultsUseCase.Response> {

    class Request{}

    class Response{}

    override fun execute(request: Request): Response {
        mtService.acceptResult()
        return Response()
    }
}