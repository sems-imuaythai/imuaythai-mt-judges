package com.imuaythai.mtjudges.login

import androidx.lifecycle.MutableLiveData
import com.imuaythai.mtjudges.common.BaseViewModel
import com.imuaythai.mtjudges.provider.MTWebService
import com.imuaythai.mtjudges.common.model.Resource
import com.imuaythai.mtjudges.login.model.LoadArtistsDataUseCase
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    var webService : MTWebService,
    var loadArtistsDataUseCase : LoadArtistsDataUseCase
) : BaseViewModel() {

    var userName : MutableLiveData<String> = MutableLiveData()

    var artistsLists : MutableLiveData<Resource<String>> = MutableLiveData()

    init {
        artistsLists.value = Resource.empty()
        userName.value = "Seweryn"
    }

    fun clicked() = execute(webService.searchArtist("aaaa"), artistsLists)

    fun clicked2() = execute(loadArtistsDataUseCase,"aaaa", artistsLists)

    fun setData(artistId: String) {

    }

}
