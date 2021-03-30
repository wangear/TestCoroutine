package com.toy.loginwork.di.component

import com.toy.loginwork.di.scopes.ActivityScope
import com.toy.loginwork.ui.activity.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface MainComponent {
    @Subcomponent.Factory
    interface Factory{
        fun create() : MainComponent
    }
    fun inject(mainActivity: MainActivity)
}