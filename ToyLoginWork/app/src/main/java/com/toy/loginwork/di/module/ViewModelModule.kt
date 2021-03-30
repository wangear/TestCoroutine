package com.toy.loginwork.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.toy.loginwork.viewmodel.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel)  :ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SigninViewModel::class)
    abstract fun bindSigninViewModel(viewModel: SigninViewModel)  :ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel)  :ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailUserViewModel::class)
    abstract fun bindDetailUserViewModel(viewModel: DetailUserViewModel)  :ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailCardViewModel::class)
    abstract fun bindDetailCardViewModel(viewModel: DetailCardViewModel)  :ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory) : ViewModelProvider.Factory
}