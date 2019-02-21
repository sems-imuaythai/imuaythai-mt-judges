package com.imuaythai.mtjudges.login

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.common.BaseActivity
import com.imuaythai.mtjudges.login.injection.LoginModule

import kotlinx.android.synthetic.main.login_activity.*
import android.widget.ArrayAdapter
import com.imuaythai.mtjudges.provider.hubservice.dto.Ring
import com.imuaythai.mtjudges.provider.hubservice.dto.UserRole


class LoginActivity : BaseActivity<LoginViewModel>() {

    override fun provideViewLayout(): Int = R.layout.login_activity

    override fun onInjectComponent(component: ApplicationComponent) = component.plus(LoginModule()).inject(this)

    override fun provideViewModel(provider: ViewModelProvider): LoginViewModel = provider.get(LoginViewModel::class.java)

    override fun onBindView(viewModel: LoginViewModel) {

        btn_settings.setOnClickListener{ _ -> viewModel.onSettingsButtonClicked() }
        submit_button.setOnClickListener { _ -> viewModel.onLoginButtonClicked(
            login_input.text.toString(),
            pass_input.text.toString()
        ) }
        viewModel.loginError.observe(this, Observer { login_input.error = it })
        viewModel.passwordError.observe(this, Observer { pass_input.error = it })
        viewModel.displayProgressLoaderAction.observe(this, Observer {
            submit_button.displayProgressBar(it)
            btn_settings.isEnabled = !it
            login_input.isEnabled = !it
            pass_input.isEnabled = !it
        })
        viewModel.errorDisplayLiveData.observe(this,  Observer { throwable -> displaySnackBarError(throwable)})
        viewModel.configurationChange.observe(this, Observer { value -> viewModel.onConfigurationChange(value) })
        viewModel.ringType.observe(this, Observer { value -> ring_text_view.text = value })
    }

}