package com.imuaythai.mtjudges.common.model


abstract class FormValidator<DATA> {

    var isValid : Boolean = false
        protected set

    abstract fun validate( data : DATA )

}