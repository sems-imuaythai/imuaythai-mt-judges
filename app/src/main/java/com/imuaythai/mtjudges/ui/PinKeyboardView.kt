package com.imuaythai.mtjudges.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.*
import com.imuaythai.mtjudges.R
import android.animation.ValueAnimator
import android.view.animation.AccelerateDecelerateInterpolator
import com.imuaythai.mtjudges.common.view.toPx


class PinKeyboardView: FrameLayout{

    constructor(context: Context) : super(context){ initialize(null); }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){ initialize(attrs); }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){ initialize(attrs); }

    private lateinit var buttons: ArrayList<Button>

    private lateinit var buttonC: Button

    private lateinit var codeInputLayout: LinearLayout

    private lateinit var codeCharDotView1: ImageView
    private lateinit var codeCharDotView2: ImageView
    private lateinit var codeCharDotView3: ImageView
    private lateinit var codeCharDotView4: ImageView
    private lateinit var codeCharDotView5: ImageView
    private lateinit var codeCharDotView6: ImageView

    private var code: String = ""

    private var onPinCompletedListener: ((pinCode: String)-> Unit)? = null

    private fun initialize(attrs: AttributeSet?){
        LayoutInflater.from(context).inflate(R.layout.pin_keyboard_layout, this)
        buttons = ArrayList()
        buttons.add(findViewById(R.id.button_1))
        buttons.add(findViewById(R.id.button_2))
        buttons.add(findViewById(R.id.button_3))
        buttons.add(findViewById(R.id.button_4))
        buttons.add(findViewById(R.id.button_5))
        buttons.add(findViewById(R.id.button_6))
        buttons.add(findViewById(R.id.button_7))
        buttons.add(findViewById(R.id.button_8))
        buttons.add(findViewById(R.id.button_9))
        buttons.add(findViewById(R.id.button_0))
        buttonC = findViewById(R.id.button_c)

        codeInputLayout = findViewById(R.id.code_input_layout)
        codeCharDotView1 = findViewById(R.id.char_input_1)
        codeCharDotView2 = findViewById(R.id.char_input_2)
        codeCharDotView3 = findViewById(R.id.char_input_3)
        codeCharDotView4 = findViewById(R.id.char_input_4)
        codeCharDotView5 = findViewById(R.id.char_input_5)
        codeCharDotView6 = findViewById(R.id.char_input_6)

        buttons.forEach { button ->
            button.setOnClickListener {
                val key = it.tag.toString()
                onClickKey(key[0])
            }
        }

        buttonC.setOnClickListener { onRemoveKey() }
        clearPinCode()
    }

    private fun onClickKey(char: Char){
        if(code.length < 6){
            code += char
            displayCode(code)
        }
        if(code.length == 6){
            onPinCompletedListener?.invoke(code)
        }
    }

    private fun onRemoveKey(){
        if(code.isNotEmpty()){
            code = (code.substring(0, code.length - 1))
            displayCode(code)
        }
    }

    private fun displayCode(code: String){
        setDotHeight(codeCharDotView1, code.length > 0)
        setDotHeight(codeCharDotView2, code.length > 1)
        setDotHeight(codeCharDotView3, code.length > 2)
        setDotHeight(codeCharDotView4, code.length > 3)
        setDotHeight(codeCharDotView5, code.length > 4)
        setDotHeight(codeCharDotView6, code.length > 5)
    }

    private fun setDotHeight( dotView: ImageView, isActive: Boolean ){
        if(isActive) {
            val anim = ValueAnimator.ofInt(dotView.measuredHeight, 28.toPx())
            anim.interpolator = AccelerateDecelerateInterpolator()
            anim.addUpdateListener { valueAnimator ->
                val value = valueAnimator.animatedValue as Int
                val layoutParams = dotView.layoutParams
                layoutParams.height = value
                dotView.layoutParams = layoutParams
            }
            anim.duration = 80
            anim.start()
        }else{
            val anim = ValueAnimator.ofInt(dotView.measuredHeight, 5.toPx())
            anim.interpolator = AccelerateDecelerateInterpolator()
            anim.addUpdateListener { valueAnimator ->
                val value = valueAnimator.animatedValue as Int
                val layoutParams = dotView.layoutParams
                layoutParams.height = value
                dotView.layoutParams = layoutParams
            }
            anim.duration = 80
            anim.start()
        }
    }

    private fun clearDotHeight(dotView: ImageView){
        val layoutParams = dotView.layoutParams
        layoutParams.height = 5.toPx()
        dotView.layoutParams = layoutParams
    }

    fun setOnPinCompletedListener(listener: (pinCode: String)-> Unit){
        onPinCompletedListener = listener
    }

    fun clearPinCode(){
        code = ""
        clearDotHeight(codeCharDotView1)
        clearDotHeight(codeCharDotView2)
        clearDotHeight(codeCharDotView3)
        clearDotHeight(codeCharDotView4)
        clearDotHeight(codeCharDotView5)
        clearDotHeight(codeCharDotView6)
        requestLayout()
        invalidate()
    }

    fun displayError(errorMessage: String){
        clearPinCode()
    }

}