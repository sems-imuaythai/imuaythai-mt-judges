package com.imuaythai.mtjudges.ui.validation

import android.content.Context
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.common.model.Validator
import javax.inject.Inject

class SpinnerValidator @Inject constructor(
    val context: Context
) : Validator<String>{

    override fun validate(value: String?) = if (value != null && !value.isEmpty()) null
        else context.getString(R.string.error_field_required)
}