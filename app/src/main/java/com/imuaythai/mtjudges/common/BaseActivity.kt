package com.imuaythai.mtjudges.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.imuaythai.mtjudges.R
import com.imuaythai.mtjudges.application.MTJudgesApplication
import com.imuaythai.mtjudges.application.injection.ApplicationComponent
import com.imuaythai.mtjudges.application.navigation.*
import com.imuaythai.mtjudges.common.model.ErrorData
import javax.inject.Inject

abstract class BaseActivity<VIEW_MODEL:BaseViewModel> : AppCompatActivity(), NavigationHandler {

    protected lateinit var viewModel: VIEW_MODEL

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onInitializeDataBinding(provideViewLayout())
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


    override fun navigate(action: RestartApplicationAction) {
        MTJudgesApplication.restartApplication(baseContext)
        startActivity(action.createIntent(baseContext))
        finish()
    }

    open fun onInitializeDataBinding(int: Int){
        setContentView(int)
    }

    fun displaySnackBarError(errorData: ErrorData?) {
        if(errorData!=null) {
            val viewGroup : ViewGroup? = findViewById(R.id.content_layout)
            if(viewGroup != null){
                val snackbar = Snackbar.make(
                    viewGroup,
                    errorData.message,
                    Snackbar.LENGTH_LONG
                )
                snackbar.setAction(errorData.actionName, {})
                snackbar.show()
            }
        }
    }

    open fun provideFragmentContainer() : Int = 0

    abstract fun provideViewLayout() : Int

    abstract fun onInjectComponent( component : ApplicationComponent)

    abstract fun provideViewModel(provider : ViewModelProvider) : VIEW_MODEL

    abstract fun onBindView(viewModel : VIEW_MODEL)

    fun setArguments(viewModel : VIEW_MODEL){}


}