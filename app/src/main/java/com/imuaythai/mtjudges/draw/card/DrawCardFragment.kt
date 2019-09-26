package com.imuaythai.mtjudges.draw.card

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.common.BaseFragment
import com.imuaythai.mtjudges.databinding.DrawCardFragmentBinding
import com.imuaythai.mtjudges.draw.card.injection.DrawCardModule

class DrawCardFragment: BaseFragment<DrawCardViewModel>(){

    override fun provideViewLayout() = R.layout.draw_card_fragment

    override fun onInjectComponent(component: ApplicationComponent) = component.plus(DrawCardModule()).inject(this)

    override fun provideViewModel(provider: ViewModelProvider): DrawCardViewModel = provider.get(DrawCardViewModel::class.java)

    override fun createView(inflater: LayoutInflater, container: ViewGroup?, viewModel: DrawCardViewModel): View {
        val binding: DrawCardFragmentBinding = DataBindingUtil.inflate(inflater, provideViewLayout(), container, false)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onBindView(viewModel: DrawCardViewModel) { }

    override fun setArguments(viewModel: DrawCardViewModel) { }
}