package com.imuaythai.mtjudges.connect

import androidx.lifecycle.ViewModelProvider
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.application.MTJudgesApplication
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.application.injection.HubConnectionComponent
import com.imuaythai.mtjudges.common.BaseActivity
import com.imuaythai.mtjudges.connect.injection.HubConnectModule

class HubConnectActivity : BaseActivity<HubConnectViewModel>(){

    override fun provideViewLayout(): Int = R.layout.connect_activity

    override fun onInjectComponent(component: ApplicationComponent) {
        val component : HubConnectionComponent? = MTJudgesApplication.get(this).hubConnectionComponent
        if(component == null){
            //todo...navigate to login
        }else{
            component.plus(HubConnectModule()).inject(this)
        }
    }

    override fun provideViewModel(provider: ViewModelProvider) = provider.get(HubConnectViewModel::class.java)

    override fun onBindView(viewModel: HubConnectViewModel) {

    }

}