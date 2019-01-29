package com.imuaythai.mtjudges.ui.validation

import android.content.Context
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.common.model.Validator
import java.util.regex.Pattern
import javax.inject.Inject


class EmailValidator @Inject constructor(
    val context: Context
) : Validator<String> {

    private val pattern: Pattern = Pattern.compile("([\\w]+([\\.-][\\w]+)*@([\\w]+([\\.-][\\w])*)+[.][A-Za-z]{2,})")

    override fun validate(value: String?): String? {
        if (value != null && !value.isEmpty()) {
            val matcher = this.pattern.matcher(value)
            return if (!matcher.matches()) {
                context.getString(R.string.error_invalid_email)
            } else null
        } else {
            return context.getString(R.string.error_field_required)
        }
    }
}