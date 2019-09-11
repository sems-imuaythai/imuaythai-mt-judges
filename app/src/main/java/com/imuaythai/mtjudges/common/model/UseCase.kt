package com.imuaythai.mtjudges.common.model

import java.lang.Exception

interface UseCase<REQUEST,RESPONSE> {

    @Throws(Exception::class)
    fun execute(request : REQUEST): RESPONSE

}