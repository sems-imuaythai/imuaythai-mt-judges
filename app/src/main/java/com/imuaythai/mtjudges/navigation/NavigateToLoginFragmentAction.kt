package com.imuaythai.mtjudges.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.imuaythai.mtjudges.application.navigation.FragmentNavigateAction
import com.imuaythai.mtjudges.login.LoginFragment

class NavigateToLoginFragmentAction constructor(
    var artistid : Int
) : FragmentNavigateAction() {

    override fun createFragment(): Fragment {
        val fragment = LoginFragment()
        val bundle = Bundle()
        bundle.putInt("artistid",artistid)
        fragment.arguments = bundle
        return fragment
    }

}