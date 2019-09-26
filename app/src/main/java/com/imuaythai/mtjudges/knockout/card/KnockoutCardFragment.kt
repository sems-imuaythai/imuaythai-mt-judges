package com.imuaythai.mtjudges.knockout.card

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.common.BaseFragment
import com.imuaythai.mtjudges.databinding.KnockoutCardFragmentBinding
import com.imuaythai.mtjudges.knockout.card.injection.KnockoutCardModule

class KnockoutCardFragment: BaseFragment<KnockoutCardViewModel>(){

    override fun provideViewLayout() = R.layout.knockout_card_fragment

    override fun onInjectComponent(component: ApplicationComponent) = component.plus(KnockoutCardModule(

    )).inject(this)

    override fun provideViewModel(provider: ViewModelProvider): KnockoutCardViewModel = provider.get(KnockoutCardViewModel::class.java)

    override fun createView(inflater: LayoutInflater, container: ViewGroup?, viewModel: KnockoutCardViewModel): View {
        val binding: KnockoutCardFragmentBinding = DataBindingUtil.inflate(inflater, provideViewLayout(), container, false)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onBindView(viewModel: KnockoutCardViewModel) { }

    override fun setArguments(viewModel: KnockoutCardViewModel) { }
}