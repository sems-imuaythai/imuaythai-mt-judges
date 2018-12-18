package com.imuaythai.mtjudges.login

import androidx.lifecycle.MutableLiveData
import com.imuaythai.mtjudges.common.BaseViewModel
import com.imuaythai.mtjudges.provider.MTWebService
import com.imuaythai.mtjudges.common.Resource
import com.imuaythai.mtjudges.login.model.LoadArtistsData
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    var webService : MTWebService,
    var loadArtistsData : LoadArtistsData
) : BaseViewModel() {

    var userName : MutableLiveData<String> = MutableLiveData()

    var artistsLists : MutableLiveData<Resource<String>> = MutableLiveData()

    init {
        artistsLists.value = Resource.empty()
        userName.value = "Seweryn"
    }

    fun clicked() = execute(webService.searchArtist("aaaa"), artistsLists)

    fun clicked2() = execute(loadArtistsData,"aaaa", artistsLists)

}
