package com.imuaythai.mtjudges.common.model

interface UseCase<REQUEST,RESPONSE> {

    fun execute(request : REQUEST) : RESPONSE;

}