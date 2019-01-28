package com.imuaythai.mtjudges.common

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.application.MTJudgesApplication
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.application.navigation.ActivityNavigationAction
import com.imuaythai.mtjudges.application.navigation.FinishActivityAction
import com.imuaythai.mtjudges.application.navigation.FragmentNavigateAction
import com.imuaythai.mtjudges.application.navigation.NavigationHandler
import javax.inject.Inject

abstract class BaseActivity<VIEW_MODEL:BaseViewModel> : AppCompatActivity(), NavigationHandler {

    private lateinit var viewModel: VIEW_MODEL

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(provideViewLayout())
        onInjectComponent(MTJudgesApplication.get(this).applicationComponent)
        viewModel = provideViewModel(ViewModelProviders.of(this, viewModelFactory))
        viewModel.fragmentNavigateAction.observe(this, Observer { action -> action.visit(this) })
        onBindView(viewModel)
        if(savedInstanceState!=null){
            setArguments(viewModel);
        }
    }

    override fun navigate(action : FragmentNavigateAction){
        if(provideFragmentContainer() != 0){
            supportFragmentManager
                .beginTransaction()
                .replace(provideFragmentContainer(), action.createFragment())
                .commit()
        }
    }

    override fun navigate(action: ActivityNavigationAction) {
        startActivity(action.createIntent(baseContext))
        if(action.closeParent){
            finish()
        }
    }

    override fun navigate(action: FinishActivityAction) {
        finish()
    }

    open fun provideFragmentContainer() : Int = 0

    abstract fun provideViewLayout() : Int

    abstract fun onInjectComponent( component : ApplicationComponent)

    abstract fun provideViewModel(provider : ViewModelProvider) : VIEW_MODEL

    abstract fun onBindView(viewModel : VIEW_MODEL)

    abstract fun setArguments(viewModel : VIEW_MODEL)

}