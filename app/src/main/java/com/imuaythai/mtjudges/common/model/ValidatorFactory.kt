package com.imuaythai.mtjudges.common.model

import android.content.Context

interface ValidatorFactory{

    fun createValidator(context: Context) : Validator<String>

}