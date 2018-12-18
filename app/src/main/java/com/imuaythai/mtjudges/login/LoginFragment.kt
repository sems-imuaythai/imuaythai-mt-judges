package com.imuaythai.mtjudges.login

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.common.BaseFragment
import com.imuaythai.mtjudges.login.injection.LoginModule
import com.imuaythai.mtjudges.common.model.Resource

import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : BaseFragment<LoginViewModel>() {

    override fun provideViewLayout(): Int = R.layout.login_fragment

    override fun onInjectComponent(component: ApplicationComponent) {
        component.plus(LoginModule()).inject(this)
    }

    override fun provideViewModel(provider: ViewModelProvider): LoginViewModel = provider.get(LoginViewModel::class.java)

    override fun onBindView(viewModel: LoginViewModel) {
        viewModel.userName.observe(this, Observer { userName -> message_text_view.text = userName })
        viewModel.artistsLists.observe(this, Observer {  data ->
            when(data.status){
                Resource.Status.LOADING -> {
                    Log.e("Resource","LOADING");
                    resource_text_view.text = "LOADING"
                }
                Resource.Status.ERROR -> {
                    Log.e("Resource","ERROR");
                    resource_text_view.text = "ERROR"
                }
                Resource.Status.SUCCESS -> {
                    Log.e("Resource","SUCCESS");
                    resource_text_view.text = "SUCCESS"
                }
                Resource.Status.EMPTY -> {
                    Log.e("Resource","EMPTY");
                    resource_text_view.text = "EMPTY"
                }
            }
        })
        click_button.setOnClickListener { _ -> viewModel.clicked() }
        click_button2.setOnClickListener { _ -> viewModel.clicked2() }
        click_button3.setOnClickListener { _ -> viewModel.clicked3() }
    }

    override fun setArguments(viewModel: LoginViewModel) {
        viewModel.setData(arguments!!.getInt("artistId",11));
    }

}

