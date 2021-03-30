package com.toy.loginwork.di.component

import com.toy.loginwork.di.scopes.ActivityScope
import com.toy.loginwork.ui.activity.DetailCardActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface DetailCardComponent {
    @Subcomponent.Factory
    interface Factory{
        fun create() : DetailCardComponent
    }
    fun inject(activity: DetailCardActivity)
}