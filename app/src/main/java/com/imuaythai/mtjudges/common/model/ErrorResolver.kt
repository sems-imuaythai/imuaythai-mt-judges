package com.imuaythai.mtjudges.common.model

interface ErrorResolver {

    fun resolveError( throwable: Throwable ) : ErrorData

}