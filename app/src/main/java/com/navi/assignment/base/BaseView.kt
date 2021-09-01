package com.navi.assignment.base

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject


interface BaseView<I : BaseIntent, S : BaseViewState, E: BaseViewEffect> {
        val _intents: PublishSubject<I>
        val intents: Observable<I>

    fun intents(): Observable<I>
    fun render(state: S)
    fun viewEffects(effect: E)
}