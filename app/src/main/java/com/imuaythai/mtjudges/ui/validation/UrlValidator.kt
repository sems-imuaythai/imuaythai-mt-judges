package com.imuaythai.mtjudges.ui.validation

import android.content.Context
import android.webkit.URLUtil
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.common.model.Validator
import javax.inject.Inject

class UrlValidator @Inject constructor(
    val context: Context
) : Validator<String>{

    override fun validate(value: String?): String? {
        if (value != null && !value.isEmpty()) {
            if(!URLUtil.isValidUrl(value)){
                return context.getString(R.string.error_incorrect_url)
            }else{
                return null
            }
        } else {
            return context.getString(R.string.error_field_required)
        }
    }
}