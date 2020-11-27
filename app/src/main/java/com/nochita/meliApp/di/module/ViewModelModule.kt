package com.nochita.meliApp.di.module

import com.nochita.meliApp.repository.SearchRepository
import com.nochita.meliApp.repository.SearchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ActivityRetainedComponent::class)
class ViewModelModule {

    @Provides
    fun provideRepository(): SearchRepository = SearchRepositoryImpl()

    @Provides
    fun provideDispatcher() : CoroutineDispatcher = Dispatchers.IO
}