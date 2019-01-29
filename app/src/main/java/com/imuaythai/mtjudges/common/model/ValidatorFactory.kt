package com.imuaythai.mtjudges.common.model

import android.content.Context

interface ValidatorFactory{

    fun create(context: Context) : Validator<String>

}