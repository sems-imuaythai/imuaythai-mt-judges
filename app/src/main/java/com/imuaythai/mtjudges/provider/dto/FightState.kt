package com.imuaythai.mtjudges.provider.dto

import java.lang.RuntimeException

enum class FightState constructor( val stateId: Int) {
    WAITING(1),
    STARTED(2),
    PAUSED(3),
    STOPPED(6),
    BREAK(7),
    ACCEPTED(8),
    DRAW(9),
    ENDED(5);

    companion object {

        fun fromStateId(stateId: Int): FightState {
            return if(stateId == 4){
                STARTED
            } else {
               values().find { state ->
                    state.stateId == stateId
                } ?: throw RuntimeException("FightState not defined")
            }
        }
    }

}