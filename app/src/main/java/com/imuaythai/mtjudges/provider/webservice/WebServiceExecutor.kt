package com.imuaythai.mtjudges.provider.webservice

import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class WebServiceExecutor @Inject constructor(){

    fun <RESPONSE> execute( callable: Call<RESPONSE>): RESPONSE{
        val response: Response<RESPONSE> = callable.execute()
        if(response.isSuccessful){
            if(response.body() == null) throw IOException()
            else return response.body()!!
        }else{
            throw IOException()
        }
    }

}