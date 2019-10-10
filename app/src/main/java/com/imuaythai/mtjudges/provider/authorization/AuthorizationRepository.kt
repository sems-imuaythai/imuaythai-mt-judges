package com.imuaythai.mtjudges.provider.authorization

import android.text.TextUtils
import com.imuaythai.mtjudges.provider.dto.ContestDto
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthorizationRepository @Inject constructor(){

    private var authToken: BehaviorSubject<String> = BehaviorSubject.create()

    private var contestDto: ContestDto? = null

    fun getAuthToken(): String? = if(!TextUtils.isEmpty(authToken.value)){
        authToken.value
    } else {
        null
    }

    fun provideAuthToken(): Single<String> = Single.just(authToken.value)

    fun setAuthToken(authToken: String){
        this.authToken.onNext(authToken)
    }

    fun getContest(): ContestDto? = contestDto

    fun setContest(contestDto: ContestDto){
        this.contestDto = contestDto
    }

    fun clearSession(){
        authToken.onNext("")
        contestDto = null
    }

}