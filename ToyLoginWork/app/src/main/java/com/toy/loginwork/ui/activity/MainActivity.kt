package com.toy.loginwork.ui.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.toy.loginwork.LoginWorkApp
import com.toy.loginwork.R
import com.toy.loginwork.databinding.ActivityMainBinding
import com.toy.loginwork.di.component.MainComponent
import com.toy.loginwork.ui.adapter.ViewPager2Adapter
import com.toy.loginwork.util.ActivityUtils
import com.toy.loginwork.viewmodel.MainViewModel
import javax.inject.Inject


class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var mainComponent: MainComponent
    private lateinit var viewModel: MainViewModel

    @Inject
    protected lateinit var mViewModelProvider: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setDI()
        setDatabinding()
        setViewModel()
        setTitleBar()
        initPagerAdapter()
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this, mViewModelProvider).get(MainViewModel::class.java)
    }

    private fun setDatabinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private fun setDI() {
        mainComponent = (applicationContext as LoginWorkApp).appComponent.mainComponent().create()
        mainComponent.inject(this)
    }

    private fun initPagerAdapter() {
        binding.viewpager.adapter = ViewPager2Adapter(this)
    }

    private fun setTitleBar() {
        ActivityUtils.setTitleBar(this, binding.toolbar)
    }
}