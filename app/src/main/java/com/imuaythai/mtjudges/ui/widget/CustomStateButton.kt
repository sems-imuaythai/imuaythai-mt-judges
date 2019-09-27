package com.imuaythai.mtjudges.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ToggleButton

class CustomStateButton: ToggleButton{

    constructor(context: Context): super(context) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int):  super(context, attrs, defStyleAttr) {}

    constructor(context: Context, attrs: AttributeSet?): super(context, attrs) {}

    override fun toggle() {
        if(!isChecked) {
            isChecked = true
        }
    }

}