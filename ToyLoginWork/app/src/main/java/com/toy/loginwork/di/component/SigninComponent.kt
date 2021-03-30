package com.toy.loginwork.di.component

import com.toy.loginwork.di.scopes.ActivityScope
import com.toy.loginwork.ui.activity.SigninActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface SigninComponent {
    @Subcomponent.Factory
    interface Factory{
        fun create() : SigninComponent
    }
    fun inject(activity: SigninActivity)
}