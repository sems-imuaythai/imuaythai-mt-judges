package com.imuaythai.mtjudges.common

interface UseCase<REQUEST,RESPONSE> {

    fun execute(request : REQUEST) : RESPONSE;

}