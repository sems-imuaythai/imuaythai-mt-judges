package com.imuaythai.mtjudges.provider.dto

data class ErrorDto(
    val type: String,
    val title: String,
    val status: Int,
    val detail: String,
    val instance: String,
    val additionalProp1: String,
    val additionalProp2: String,
    val additionalProp3: String
)