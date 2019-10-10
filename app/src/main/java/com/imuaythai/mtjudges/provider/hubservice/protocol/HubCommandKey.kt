package com.imuaythai.mtjudges.provider.hubservice.protocol

import dagger.MapKey

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class HubCommandKey(val value: HubCommand)