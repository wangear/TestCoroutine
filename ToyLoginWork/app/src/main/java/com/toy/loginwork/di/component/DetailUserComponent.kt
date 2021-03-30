package com.toy.loginwork.di.component

import com.toy.loginwork.di.scopes.ActivityScope
import com.toy.loginwork.ui.activity.DetailUserActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface DetailUserComponent {
    @Subcomponent.Factory
    interface Factory{
        fun create() : DetailUserComponent
    }
    fun inject(activity: DetailUserActivity)
}