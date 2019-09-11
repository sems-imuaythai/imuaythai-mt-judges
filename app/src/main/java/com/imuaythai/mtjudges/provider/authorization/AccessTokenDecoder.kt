package com.imuaythai.mtjudges.provider.authorization

import com.imuaythai.mtjudges.provider.dto.AccessTokenDto
import com.imuaythai.mtjudges.provider.dto.UserDataDto
import com.imuaythai.mtjudges.provider.dto.UserRole
import javax.inject.Inject

class AccessTokenDecoder @Inject constructor(){

    fun decodeUserData(accessToken: AccessTokenDto): UserDataDto {
        return UserDataDto(
            "[username]",
            UserRole.POINT_JUDGE
        )
    }

}