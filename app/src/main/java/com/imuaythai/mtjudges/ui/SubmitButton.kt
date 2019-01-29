package com.imuaythai.mtjudges.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.imuaythai.mtjudges.R

class SubmitButton : FrameLayout{

    constructor(context: Context) : super(context){ initialize(null); }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){ initialize(attrs); }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){ initialize(attrs); }

    private lateinit var button : Button
    private lateinit var progressBar : ProgressBar

    private var buttonText : String = ""

    private fun initialize(attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.submit_button_layout, this)
        progressBar = findViewById(R.id.progress_bar)
        button = findViewById(R.id.button)

        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SubmitButton)
            val text = typedArray.getString(R.styleable.SubmitButton_text)
            typedArray.recycle()
            if(text != null)setText(text)
        }
        displayProgressBar(false)
    }

    fun setText(string: String){
        buttonText = string
        button.text = string
    }

    fun displayProgressBar(isLoading : Boolean){
        if(isLoading) {
            progressBar.visibility = View.VISIBLE
            button.isEnabled = false
            button.text = null
        }else{
            progressBar.visibility = View.GONE
            button.isEnabled = true
            button.text = buttonText
        }
    }

    override fun setOnClickListener(l: OnClickListener?) {
        button.setOnClickListener(l)
    }

}