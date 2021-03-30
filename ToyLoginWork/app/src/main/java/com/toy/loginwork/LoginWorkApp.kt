package com.toy.loginwork

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.toy.loginwork.di.component.ApplicationComponent
import com.toy.loginwork.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class LoginWorkApp : Application(), HasAndroidInjector {
    val isLogin = MutableLiveData<Boolean>()

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector


}