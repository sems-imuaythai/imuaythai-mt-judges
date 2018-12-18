package com.imuaythai.mtjudges.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.imuaythai.mtjudges.application.MTJudgesApplication
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.application.navigation.NavigationHandler
import javax.inject.Inject

abstract class BaseFragment<VIEW_MODEL:BaseViewModel> : Fragment() {

    private lateinit var viewModel: VIEW_MODEL

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onInjectComponent(MTJudgesApplication.get(requireContext()).applicationComponent)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(provideViewLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = provideViewModel(ViewModelProviders.of(this, viewModelFactory))
        viewModel.fragmentNavigateAction.observe(this, Observer { action -> action.visit( activity as NavigationHandler ) })
        onBindView(viewModel)
    }

    abstract fun provideViewLayout() : Int

    abstract fun onInjectComponent( component : ApplicationComponent )

    abstract fun provideViewModel(provider : ViewModelProvider) : VIEW_MODEL

    abstract fun onBindView(viewModel : VIEW_MODEL)

    abstract fun setArguments(viewModel : VIEW_MODEL)

}