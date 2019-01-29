package com.imuaythai.mtjudges.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imuaythai.mtjudges.application.navigation.FragmentNavigateAction
import com.imuaythai.mtjudges.application.navigation.NavigateAction
import com.imuaythai.mtjudges.common.model.ErrorData
import com.imuaythai.mtjudges.common.model.ErrorResolver
import com.imuaythai.mtjudges.common.model.Resource
import com.imuaythai.mtjudges.common.model.UseCase
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

open class BaseViewModel : ViewModel() {

    val fragmentNavigateAction : MutableLiveData<NavigateAction> = MutableLiveData()

    val displayProgressLoaderAction : MutableLiveData<Boolean> = MutableLiveData()

    val errorDisplayLiveData : MutableLiveData<ErrorData> = MutableLiveData()

    private val disposables = CompositeDisposable()

    fun <REQUEST,RESPONSE> execute(useCase: UseCase<REQUEST, RESPONSE>, request : REQUEST, onSuccess : Consumer<in RESPONSE>, onError : Consumer<in Throwable> ){
        displayProgress()
        disposables.add(Observable.create(ObservableOnSubscribe<RESPONSE> { subscriber ->
            try {
                subscriber.onNext(useCase.execute(request))
                subscriber.onComplete()
                hideProgress()
            }catch (throwable : Throwable){
                subscriber.onError(throwable)
                hideProgress()
            }
        })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.newThread())
        .subscribe(onSuccess, onError))
    }

    fun <RESPONSE> execute( observable : Observable<RESPONSE>, onSuccess : Consumer<in RESPONSE>, onError : Consumer<in Throwable> ){
        displayProgress()
        disposables.add(observable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe({
                onSuccess.accept(it)
                hideProgress()
            }, {
                onError.accept(it)
                hideProgress()
            }))
    }

    fun <RESPONSE> execute( observable : Observable<RESPONSE>, onSuccess : Consumer<in RESPONSE>, resolver: ErrorResolver ){
        displayProgress()
        disposables.add(observable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe({
                onSuccess.accept(it)
                hideProgress()
            }, {
                displayError(resolver.resolveError(it))
                hideProgress()
            }))
    }

    fun <REQUEST,RESPONSE> execute(useCase: UseCase<REQUEST, RESPONSE>, request : REQUEST, liveData: MutableLiveData<Resource<RESPONSE>> ){
        liveData.value = Resource.loading()
        execute(useCase,request,Consumer { response ->
            liveData.value = Resource.success(response)
        }, Consumer{ throwable ->
            liveData.value = Resource.error(throwable)
        })
    }

    fun <RESPONSE> execute( observable : Observable<RESPONSE>, liveData: MutableLiveData<Resource<RESPONSE>> ){
        liveData.value = Resource.loading()
        execute(observable,Consumer { response ->
            liveData.value = Resource.success(response)
        }, Consumer{ throwable ->
            liveData.value = Resource.error(throwable)
        })
    }

    override fun onCleared() {
        disposables.clear()
    }

    fun navigate(actionFragment : NavigateAction){
        fragmentNavigateAction.value = actionFragment
    }

    fun displayProgress() {
        displayProgressLoaderAction.value = true
    }

    fun hideProgress() {
        displayProgressLoaderAction.value = false
    }

    fun displayError(errorData: ErrorData){
        errorDisplayLiveData.value = errorData
    }

}