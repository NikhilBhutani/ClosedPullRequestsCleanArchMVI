package com.navi.assignment.di

import com.navi.assignment.application.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        RemoteModule::class,
        DomainModule::class,
        AndroidInjectionModule::class,
        ActivityBuilderModule::class]
)
interface AppComponent : AndroidInjector<App> {
    override fun inject(app: App)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): Builder
        fun build(): AppComponent
    }

}