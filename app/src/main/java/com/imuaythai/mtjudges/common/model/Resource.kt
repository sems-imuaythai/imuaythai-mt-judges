package com.imuaythai.mtjudges.common.model

class Resource<T> private constructor(val status: Status, val data: T?, val exception: Throwable?) {
    enum class Status {
        SUCCESS, ERROR, LOADING, EMPTY
    }
    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }
        fun <T> error(exception: Throwable?): Resource<T> {
            return Resource(
                Status.ERROR,
                null,
                exception
            )
        }
        fun <T> loading(): Resource<T> {
            return Resource(
                Status.LOADING,
                null,
                null
            )
        }
        fun <T> empty(): Resource<T> {
            return Resource(
                Status.EMPTY,
                null,
                null
            )
        }
    }
}