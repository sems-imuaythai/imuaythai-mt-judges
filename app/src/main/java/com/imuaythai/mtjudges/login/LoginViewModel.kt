package com.imuaythai.mtjudges.login

import androidx.lifecycle.MutableLiveData
import com.imuaythai.mtjudges.common.BaseViewModel
import com.imuaythai.mtjudges.provider.MTWebService
import com.imuaythai.mtjudges.common.model.Resource
import com.imuaythai.mtjudges.login.model.LoadArtistsDataUseCase
import com.imuaythai.mtjudges.navigation.NavigateToSettingsActivityAction
import com.imuaythai.mtjudges.navigation.NavigateToTimeJudgeFragmentAction
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

    fun clicked3() {
        navigate(NavigateToTimeJudgeFragmentAction())
    }

    fun setData(artistId: Int) {

    }

    fun onSettingsButtonClicked() = navigate(NavigateToSettingsActivityAction())

}
