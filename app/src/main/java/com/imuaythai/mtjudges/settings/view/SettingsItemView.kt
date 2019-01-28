package com.imuaythai.mtjudges.settings.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView

import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.settings.model.SettingsItem

class SettingsItemView : FrameLayout{
    constructor(context: Context) : super(context){ initialize(null); }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){ initialize(attrs); }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){ initialize(attrs); }

    private lateinit var titleTextView : TextView
    private lateinit var valueTextView : TextView

    private fun initialize(attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.settings_item_view, this)
        titleTextView = findViewById(R.id.settings_title)
        valueTextView = findViewById(R.id.settings_value)

        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SettingsItemView)
            val title = typedArray.getString(R.styleable.SettingsItemView_item_title)
            val value = typedArray.getString(R.styleable.SettingsItemView_item_value)
            typedArray.recycle()
            if(title != null)setTitle(title)
            if(value != null)setValue(value)
        }
    }

    fun setTitle(title: String) {
        titleTextView.text = title
    }

    fun setValue(value: String) {
        valueTextView.text = value
    }

    fun display(settingsItem: SettingsItem){
        setTitle(settingsItem.name)
        setValue(settingsItem.value)
    }

}