package com.imuaythai.mtjudges.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.imuaythai.mtjudges.application.MTJudgesApplication
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.login.injection.LoginModule
import com.imuaythai.mtjudges.provider.Resource

import kotlinx.android.synthetic.main.login_fragment.*
import javax.inject.Inject

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MTJudgesApplication.get(requireContext())
            .applicationComponent
            .plus(LoginModule())
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
        viewModel.userName.observe(this, Observer { userName -> message_text_view.text = userName })
        viewModel.data.observe(this, Observer {  data ->
            when(data.status){
                Resource.Status.LOADING -> {
                    Log.e("Resource","LOADING");
                }
                Resource.Status.ERROR -> {
                    Log.e("Resource","ERROR");
                }
                Resource.Status.SUCCESS -> {
                    Log.e("Resource","SUCCESS");
                }
            }
        })
        click_button.setOnClickListener { _ -> viewModel.clicked() }
    }

}