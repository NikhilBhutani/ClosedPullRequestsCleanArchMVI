package com.navi.assignment.application

import com.navi.assignment.common.NetworkManager
import com.navi.assignment.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App: DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        NetworkManager.startNetworkCallback(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}