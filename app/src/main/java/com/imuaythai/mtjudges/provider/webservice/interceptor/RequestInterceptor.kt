package com.imuaythai.mtjudges.provider.webservice.interceptor

import com.imuaythai.mtjudges.provider.authorization.AuthorizationRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class RequestInterceptor @Inject constructor(
    private val authorizationRepository: AuthorizationRepository
): Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val builder = original.newBuilder().apply {
            header("Content-Type","application/json")
            method(original.method(), original.body())
        }

        if(original.header("No-Authentication") == null){
            val authToken = authorizationRepository.getAuthToken();
            builder.header("Authorization", "Bearer $authToken")
        }

        return chain.proceed(builder.build())
    }
}