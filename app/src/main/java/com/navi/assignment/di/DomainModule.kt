package com.navi.assignment.di

import com.navi.assignment.domain.schedulers.SchedulerProvider
import com.navi.assignment.domain.schedulers.SchedulerProviderImpl
import dagger.Binds
import dagger.Module


@Module
abstract class DomainModule {
    @Binds
    abstract fun bindsSchedulerProvider(schedulerProviderImpl: SchedulerProviderImpl): SchedulerProvider
}