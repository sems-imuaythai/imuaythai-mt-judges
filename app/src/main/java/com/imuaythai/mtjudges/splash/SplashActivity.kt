package com.imuaythai.mtjudges.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.login.LoginFragment

import kotlinx.android.synthetic.main.splash_activity.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container,LoginFragment.newInstance())
                .commit()
        }
    }

}