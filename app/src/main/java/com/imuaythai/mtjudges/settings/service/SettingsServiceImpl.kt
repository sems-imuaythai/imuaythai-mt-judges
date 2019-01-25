package com.imuaythai.mtjudges.settings.service

import android.content.SharedPreferences
import javax.inject.Inject

class SettingsServiceImpl @Inject constructor(
    var sharedPreferences : SharedPreferences
): SettingsService {

    private var PREFIX : String = "com.imuaythai.mtjudges.";

    override fun provideHost() = sharedPreferences.getString(PREFIX+"host",defaultHost()) ?: defaultHost()

    private fun defaultHost(): String = "https://imuaythai-api.herokuapp.com"
}