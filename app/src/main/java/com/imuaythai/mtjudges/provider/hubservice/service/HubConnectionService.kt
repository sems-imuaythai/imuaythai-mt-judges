package com.imuaythai.mtjudges.provider.hubservice.service
import androidx.lifecycle.LiveData
import com.imuaythai.mtjudges.provider.hubservice.dto.FightListItem
import com.imuaythai.mtjudges.provider.hubservice.dto.Ring
import com.imuaythai.mtjudges.provider.hubservice.dto.UserRole

interface HubConnectionService : HubStateService {

    fun provideFightsList() : LiveData<List<FightListItem>>

    fun connect( ring : Ring, userId : String, userRole : UserRole )

    fun getFightList( ring : Ring, contestId : Int )

    fun enterToFight( fightId : Int )

}