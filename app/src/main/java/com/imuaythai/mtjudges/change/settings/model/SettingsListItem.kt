package com.imuaythai.mtjudges.change.settings.model

import java.io.Serializable

class SettingsListItem constructor(
    val label : String,
    val value : String,
    var isSelected: Boolean = false
){}