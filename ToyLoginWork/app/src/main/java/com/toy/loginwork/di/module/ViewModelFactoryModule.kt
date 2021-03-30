package com.toy.loginwork.di.module

import androidx.lifecycle.ViewModelProvider
import com.toy.loginwork.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}