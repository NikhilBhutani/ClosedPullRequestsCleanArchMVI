package com.navi.assignment.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.navi.assignment.viewmodelfactory.AppViewModelFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<VM : ViewModel> : DaggerAppCompatActivity() {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var appViewModelFactory: AppViewModelFactory

    abstract val clazz: Class<VM>

    val vm: VM by lazy {
        ViewModelProvider(this, appViewModelFactory).get(clazz)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initView()
        bind()
    }

    @LayoutRes
    protected abstract fun layoutId(): Int

    protected abstract fun initView()

    protected abstract fun bind()

    override fun androidInjector(): AndroidInjector<Any> = injector
}