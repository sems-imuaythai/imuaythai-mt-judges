package com.imuaythai.mtjudges.common.rx

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.imuaythai.mtjudges.common.model.Resource
import com.imuaythai.mtjudges.common.model.UseCase
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UseCaseWrap <REQUEST,RESPONSE> constructor(
    private val request: REQUEST,
    private val disposable: CompositeDisposable,
    private val useCase: UseCase<REQUEST,RESPONSE>
){

    private var loaderLiveData: MutableLiveData<Boolean>? = null

    fun withLoader( loaderLiveData: MutableLiveData<Boolean> ): UseCaseWrap <REQUEST,RESPONSE>{
        this.loaderLiveData = loaderLiveData
        return this
    }

    fun bind(resource: MutableLiveData<Resource<RESPONSE>>){
        resource.value = Resource.loading();
        disposable.add(
            Observable.create(ObservableOnSubscribe<RESPONSE> { subscriber ->
                Log.i(useCase::class.java.simpleName,"execute")
                try {
                    val response = useCase.execute(request)
                    Log.i(useCase::class.java.simpleName,"complete")
                    subscriber.onComplete()
                    if(response == null) {
                        resource.postValue(Resource.empty())
                    } else {
                        resource.postValue(Resource.success(response))
                    }
                }catch (throwable : Throwable){
                    Log.e(useCase::class.java.simpleName,"error")
                    resource.postValue(Resource.error(throwable))
                }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe())
    }

    fun subscribe(onSuccess : (RESPONSE) -> Unit, onError : (Throwable) -> Unit = {}){
        loaderLiveData?.value = true
        disposable.add(
            Observable.create(ObservableOnSubscribe<RESPONSE> { subscriber ->
                Log.i(useCase::class.java.simpleName,"execute")
                try {
                    subscriber.onNext(useCase.execute(request))
                    Log.i(useCase::class.java.simpleName,"complete")
                    subscriber.onComplete()
                    loaderLiveData?.postValue(false)
                }catch (throwable : Throwable){
                    Log.e(useCase::class.java.simpleName,"error")
                    subscriber.onError(throwable)
                    loaderLiveData?.postValue(false)
                }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe(onSuccess, onError))
    }

}