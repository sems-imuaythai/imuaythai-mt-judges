package com.imuaythai.mtjudges.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imuaythai.mtjudges.login.service.LoginService
import com.imuaythai.mtjudges.provider.MTWebService
import com.imuaythai.mtjudges.provider.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import io.reactivex.disposables.CompositeDisposable

class LoginViewModel @Inject constructor(
    var loginService : LoginService,
    var webService : MTWebService
) : ViewModel() {

    var userName : MutableLiveData<String> = MutableLiveData()

    var data : MutableLiveData<Resource<String>> = MutableLiveData()

    private val disposables = CompositeDisposable()

    fun clicked() {
        load();
        this.userName.value = loginService.login("asd","asdas");
    }

    fun load() {
        disposables.add(webService.searchArtist("aaaa")
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                Resource.success(response)
            }, { exception ->
                data.value = Resource.error( exception)
            }))
    }

    override fun onCleared() {
        disposables.clear()
    }

}
