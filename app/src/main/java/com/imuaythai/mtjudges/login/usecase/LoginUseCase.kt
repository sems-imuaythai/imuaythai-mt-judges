package com.imuaythai.mtjudges.login.usecase

import com.imuaythai.mtjudges.common.model.UseCase
import com.imuaythai.mtjudges.service.MTService
import com.imuaythai.mtjudges.provider.dto.UserRole
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val mtService: MTService
): UseCase<LoginUseCase.Request,LoginUseCase.Response>{

    data class Request(
        val pinCode: String
    )

    data class Response(
        val userName: String,
        val userRole: UserRole
    )

    override fun execute(request: Request): Response {
        Thread.sleep(2000)
        val response = mtService.login("456321")
        return Response(
            response.familyName,
            UserRole.POINT_JUDGE
        )
    }
}