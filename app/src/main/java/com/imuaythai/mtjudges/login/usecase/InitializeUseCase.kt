package com.imuaythai.mtjudges.login.usecase

import com.imuaythai.mtjudges.common.model.UseCase
import com.imuaythai.mtjudges.provider.dto.Ring
import com.imuaythai.mtjudges.provider.dto.FightDataDto
import com.imuaythai.mtjudges.service.MTService
import javax.inject.Inject

class InitializeUseCase @Inject constructor(
    private val mtService: MTService
): UseCase<InitializeUseCase.Request, FightDataDto>{

    class Request{}

    override fun execute(request: Request): FightDataDto {
        return mtService.initialize(Ring.A)
    }

}

