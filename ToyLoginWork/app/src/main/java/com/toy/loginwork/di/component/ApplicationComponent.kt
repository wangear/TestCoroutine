package com.toy.loginwork.di.component

import android.content.Context
import com.toy.loginwork.di.builder.ActivityBuilder
import com.toy.loginwork.di.module.NetworkModule
import com.toy.loginwork.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ActivityBuilder::class, ViewModelModule::class,NetworkModule::class])
interface ApplicationComponent {
    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : ApplicationComponent
    }

    fun mainComponent() : MainComponent.Factory
    fun signinComponent() : SigninComponent.Factory
    fun loginComponent() : LoginComponent.Factory
    fun detailUserComponent() : DetailUserComponent.Factory
    fun detailCardComponent() : DetailCardComponent.Factory
}