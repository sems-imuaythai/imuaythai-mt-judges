package com.imuaythai.mtjudges.fight.header

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.common.BaseFragment
import com.imuaythai.mtjudges.fight.header.injection.FightHeaderModule
import androidx.databinding.DataBindingUtil
import com.imuaythai.mtjudges.databinding.HeaderFragmentBinding


class FightHeaderFragment: BaseFragment<FightHeaderViewModel>(){

    override fun provideViewLayout() = com.imuaythai.mtjudges.R.layout.header_fragment

    override fun onInjectComponent(component: ApplicationComponent) = component.plus(FightHeaderModule()).inject(this)

    override fun createView(inflater: LayoutInflater, container: ViewGroup?, viewModel: FightHeaderViewModel): View {
        val binding: HeaderFragmentBinding = DataBindingUtil.inflate(inflater, provideViewLayout(), container, false)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun provideViewModel(provider: ViewModelProvider) = provider.get(FightHeaderViewModel::class.java)

    override fun setArguments(viewModel: FightHeaderViewModel) { }

    override fun onBindView(viewModel: FightHeaderViewModel) { }

}