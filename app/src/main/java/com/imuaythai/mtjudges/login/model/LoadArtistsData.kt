package com.imuaythai.mtjudges.login.model

import com.imuaythai.mtjudges.common.UseCase
import com.imuaythai.mtjudges.provider.MTWebService
import javax.inject.Inject

class LoadArtistsData @Inject constructor(
    var webService : MTWebService
) : UseCase<String,String>{

    override fun execute(request: String): String {
        Thread.sleep(1000)
        return webService.searchArtist(request).blockingSingle()
    }
}