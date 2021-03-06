package com.imuaythai.mtjudges.common.exception

import android.content.Context
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.common.model.ErrorData
import com.imuaythai.mtjudges.common.model.ErrorResolver
import com.imuaythai.mtjudges.common.rx.RetrofitException
import com.imuaythai.mtjudges.provider.webservice.dto.ErrorResponse
import java.io.IOException
import javax.inject.Inject

class LoginErrorResolver @Inject constructor(
    val context: Context
) : ErrorResolver {

    override fun resolveError(throwable: Throwable): ErrorData {
        if (throwable is RetrofitException){
            val response : ErrorResponse? = throwable.getErrorBodyAs(ErrorResponse::class.java)
            return if(response!=null)
                ErrorData(response.message)
            else
                ErrorData(throwable.message ?: context.getString(R.string.error_generic_message))
        }else if(throwable is IOException){
            return ErrorData(context.getString(R.string.error_generic_message))
        }else{
            return ErrorData(context.getString(R.string.error_generic_message))
        }
    }
}