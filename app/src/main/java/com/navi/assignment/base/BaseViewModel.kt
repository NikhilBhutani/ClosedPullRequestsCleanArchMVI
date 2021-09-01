package com.navi.assignment.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.FlowableTransformer
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.subjects.PublishSubject

abstract class BaseViewModel<I : BaseIntent, A : BaseAction, R : BaseResult, S : BaseViewState, E : BaseViewEffect> :
    ViewModel() {

    val intentsSubject: PublishSubject<I> = PublishSubject.create()

    val statesObservable: Flowable<S> by lazy {
        compose()
    }

    abstract fun intentFilter(): FlowableTransformer<I, I>

    abstract fun actionFromIntent(intent: I): A

    protected abstract val actionProcessor: BaseActionProcessor<A, R>

    abstract fun reducer(): BiFunction<S, R, S>

    abstract fun initialState(): S

    abstract fun viewEffect(): Flowable<E>

    abstract fun provisionViewEffect(): @NonNull Consumer<in R>?

    fun processIntents(intents: Observable<I>) {
       intents.subscribe(intentsSubject)
    }

    fun getStates(): Flowable<S> {
       return statesObservable
    }

    fun getViewEffects(): Flowable<E> {
      return viewEffect()
    }

    private fun compose(): Flowable<S> {
        return intentsSubject
            .toFlowable(BackpressureStrategy.LATEST)
            .compose(intentFilter())
            .map(::actionFromIntent)
            .compose(actionProcessor.transformFromAction())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterNext(provisionViewEffect())
            .scan(initialState(), reducer())
            .distinctUntilChanged()
    }
}