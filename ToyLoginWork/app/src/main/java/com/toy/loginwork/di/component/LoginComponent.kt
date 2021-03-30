package com.toy.loginwork.di.component

import com.toy.loginwork.di.scopes.ActivityScope
import com.toy.loginwork.ui.activity.LoginActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface LoginComponent {
    @Subcomponent.Factory
    interface Factory{
        fun create() : LoginComponent
    }
    fun inject(activity: LoginActivity)
}