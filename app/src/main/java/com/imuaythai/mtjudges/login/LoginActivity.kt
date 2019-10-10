package com.imuaythai.mtjudges.login

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.common.BaseActivity
import com.imuaythai.mtjudges.common.model.Resource
import com.imuaythai.mtjudges.login.injection.LoginModule

import kotlinx.android.synthetic.main.login_activity.*
import com.imuaythai.mtjudges.common.view.setIsVisibleOrGone

class LoginActivity: BaseActivity<LoginViewModel>() {

    override fun provideViewLayout(): Int = R.layout.login_activity

    override fun onInjectComponent(component: ApplicationComponent) = component.plus(LoginModule()).inject(this)

    override fun provideViewModel(provider: ViewModelProvider): LoginViewModel = provider.get(LoginViewModel::class.java)

    override fun onBindView(viewModel: LoginViewModel) {

        ring_text_view.text = viewModel.ringType
        disconnected_view.setOnClickListener { viewModel.initializeRequest() }
        btn_settings.setOnClickListener{ viewModel.onSettingsButtonClicked() }
        pin_keyboard.setOnPinCompletedListener{ pinCode -> viewModel.pinLoginRequest(pinCode) }

        viewModel.fightDataResource.observe(this, Observer {
            when(it.status){
                Resource.Status.SUCCESS -> {
                    red_fighter_name.text = it.data?.redFighterName
                    blue_fighter_name.text = it.data?.blueFighterName
                    fight_name.text = it.data?.weightCategory
                }
                Resource.Status.EMPTY -> {}
                Resource.Status.LOADING -> {}
                Resource.Status.ERROR -> {}
            }
            disconnected_view.setIsVisibleOrGone(it.status == Resource.Status.ERROR)
            progress_view.setIsVisibleOrGone(it.status == Resource.Status.LOADING)
            login_form.setIsVisibleOrGone(it.status == Resource.Status.SUCCESS)
            empty_view.setIsVisibleOrGone(it.status == Resource.Status.EMPTY)
        })

        viewModel.isScreenLoading.observe(this, Observer {
            if(it == true){
                progress_view.setIsVisibleOrGone(true)
                container.setIsVisibleOrGone(false)
            }else{
                progress_view.setIsVisibleOrGone(false)
                container.setIsVisibleOrGone(true)
            }
        })

        viewModel.pinLoginError.observe(this, Observer {
            val message = it?: "Incorrect password"
            pin_keyboard.displayError(message)
            Snackbar.make(content_layout, message, Snackbar.LENGTH_LONG).show();
        })

    }

}
