package com.navi.assignment.di

import androidx.lifecycle.ViewModel
import com.navi.assignment.data.repository.PullRequestRepositoryImpl
import com.navi.assignment.domain.repository.PullRequestRepository
import com.navi.assignment.presentation.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMyViewModel(mainViewModel: MainViewModel) : ViewModel

    @Binds
    abstract fun bindsPullRequestRepository(pullRequestRepositoryImpl: PullRequestRepositoryImpl): PullRequestRepository

}