package com.toy.loginwork.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.toy.loginwork.Constants
import com.toy.loginwork.LoginWorkApp
import com.toy.loginwork.R
import com.toy.loginwork.databinding.ActivityDetailUserBinding
import com.toy.loginwork.di.component.DetailUserComponent
import com.toy.loginwork.util.ActivityUtils
import com.toy.loginwork.viewmodel.DetailUserViewModel
import javax.inject.Inject


class DetailUserActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    lateinit var component: DetailUserComponent
    private lateinit var viewModel: DetailUserViewModel

    @Inject
    protected lateinit var mViewModelProvider: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setDI()
        setDatabinding()
        setViewModel()
        setTitleBar()
        setObserver()
        setRefreshListener()
        setIntentData(intent)
        viewModel.requestUserDetail()
    }

    private fun setRefreshListener() {
        binding.slDetailUser.setOnRefreshListener {
            viewModel.requestUserDetail()
        }
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this, mViewModelProvider).get(DetailUserViewModel::class.java)
    }

    private fun setDatabinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_user)
    }

    private fun setDI() {
        component = (applicationContext as LoginWorkApp).appComponent.detailUserComponent().create()
        component.inject(this)
    }

    private fun setObserver() {
        viewModel.user.observe(this, Observer {
            binding.tvUser.text = getString(R.string.default_nickname) + it.nickname
            binding.tvIntro.text = getString(R.string.default_intro) + it.introduction
            binding.slDetailUser.isRefreshing = false
        })
    }


    private fun setIntentData(intent: Intent) {
        var id = intent.getStringExtra(Constants.ID)
        id?.let{
            viewModel.setId(it)
        }
    }

    private fun setTitleBar() {
        ActivityUtils.setTitleBar(this, binding.toolbar)
    }

}