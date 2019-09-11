package com.imuaythai.mtjudges.common.view

import android.content.res.Resources
import android.view.View
import androidx.fragment.app.Fragment
import java.io.Serializable

fun View.setIsVisibleOrGone(isVisible : Boolean?){
    if(isVisible == null || !isVisible){
        visibility = View.GONE
    }else{
        visibility = View.VISIBLE
    }
}

fun View.setIsVisibleOrInvisible(isVisible : Boolean?){
    if(isVisible == null || !isVisible){
        visibility = View.INVISIBLE
    }else{
        visibility = View.VISIBLE
    }
}

fun Fragment.setArgument( name : String ,data : Serializable){
    if(arguments == null){
        arguments = android.os.Bundle()
    }
    arguments?.putSerializable(name,data)
}

fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()