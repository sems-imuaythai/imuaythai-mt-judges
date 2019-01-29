package com.imuaythai.mtjudges.common.model

interface Validator<V> {

    fun validate( value : V? ) : String?

}