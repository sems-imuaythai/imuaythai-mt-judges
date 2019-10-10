package com.imuaythai.mtjudges.provider

import android.os.Handler
import android.os.Looper
import com.imuaythai.mtjudges.provider.dto.FightDataDto
import com.imuaythai.mtjudges.provider.dto.FightStatusDto
import com.imuaythai.mtjudges.provider.dto.UserDataDto
import com.imuaythai.mtjudges.provider.dto.UserRole
import com.imuaythai.mtjudges.service.FightDataStore
import com.imuaythai.mtjudges.service.FightRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FightRepositoryImpl @Inject constructor(): FightRepository, FightDataStore {

    private var fightDataDto: FightDataDto? = null

    private lateinit var userDataDto: UserDataDto

    private val handler = Handler(Looper.getMainLooper())

    private var fightStatus: BehaviorSubject<FightStatusDto> = BehaviorSubject.create()

    override fun provideFightStatusObservable(): Observable<FightStatusDto>{
        return fightStatus.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getFightData(): FightDataDto? {
        return fightDataDto
    }

    override fun saveFight(fightDataDto: FightDataDto) {
        this.fightDataDto = fightDataDto
    }

    override fun saveUserData(userDataDto: UserDataDto) {
        this.userDataDto = userDataDto

    }

    override fun updateFightStatus(fightStatusDto: FightStatusDto) {
        handler.post {
            fightStatus.onNext(fightStatusDto)
        }
    }

    override fun getUserData(): UserDataDto = userDataDto

    override fun getUserRole(): UserRole = UserRole.POINT_JUDGE

}