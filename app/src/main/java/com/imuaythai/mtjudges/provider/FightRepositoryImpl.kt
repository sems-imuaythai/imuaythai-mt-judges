package com.imuaythai.mtjudges.provider

import com.imuaythai.mtjudges.provider.dto.FightDataDto
import com.imuaythai.mtjudges.provider.dto.FightStatusDto
import com.imuaythai.mtjudges.service.FightDataStore
import com.imuaythai.mtjudges.service.FightRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FightRepositoryImpl @Inject constructor(): FightRepository, FightDataStore {

    private lateinit var fightDataDto: FightDataDto

    private var fightStatus: BehaviorSubject<FightStatusDto> = BehaviorSubject.create()

    override fun provideFightStatusObservable(): Observable<FightStatusDto>{
        return fightStatus.subscribeOn(AndroidSchedulers.mainThread())
    }

    override fun getFightData(): FightDataDto {
        return fightDataDto
    }

    override fun saveFight(fightDataDto: FightDataDto) {
        this.fightDataDto = fightDataDto
    }

    override fun updateFightStatus(fightStatusDto: FightStatusDto) {
        fightStatus.onNext(fightStatusDto)
    }
}