package com.toy.loginwork.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.toy.loginwork.Constants
import com.toy.loginwork.LoginWorkApp
import com.toy.loginwork.R
import com.toy.loginwork.databinding.ActivityDetailCardBinding
import com.toy.loginwork.di.component.DetailCardComponent
import com.toy.loginwork.repository.data.PopularCardData
import com.toy.loginwork.ui.adapter.PictureListAdapter
import com.toy.loginwork.ui.adapter.PopCardListAdapter
import com.toy.loginwork.util.ActivityUtils
import com.toy.loginwork.viewmodel.DetailCardViewModel
import javax.inject.Inject


class DetailCardActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailCardBinding
    lateinit var component: DetailCardComponent
    private lateinit var viewModel: DetailCardViewModel

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
        viewModel.requestCardDetail()
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this, mViewModelProvider).get(DetailCardViewModel::class.java)
    }

    private fun setDatabinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_card)
    }

    private fun setDI() {
        component = (applicationContext as LoginWorkApp).appComponent.detailCardComponent().create()
        component.inject(this)
    }

    private fun setObserver() {
        viewModel.card.observe(this, Observer {
            binding.tvDetail.text = getString(R.string.default_card_detail) + it.card.description
            binding.tvUser.text = getString(R.string.default_nickname) + it.user.nickname
            binding.tvIntro.text = getString(R.string.default_intro) + it.user.introduction

            Glide.with(this).load(it.card.imgUrl).into(binding.ivDetail)
            if (binding.rvDetailPicture.adapter == null) {
                initCardAdapater(it.recommendCards)
            } else {
                (binding.rvDetailPicture.adapter!! as PopCardListAdapter).updateData(it.recommendCards)
                binding.rvDetailPicture.adapter?.notifyDataSetChanged()
            }
            binding.slDetailCard.isRefreshing = false
        })
    }

    private fun setRefreshListener() {
        binding.slDetailCard.setOnRefreshListener {
            viewModel.requestCardDetail()
        }
    }

    private fun initCardAdapater(list: List<PopularCardData>) {
        binding.rvDetailPicture.adapter = PopCardListAdapter(list)
    }

    private fun setTitleBar() {
        ActivityUtils.setTitleBar(this, binding.toolbar)
    }

    private fun setIntentData(intent: Intent) {
        var id = intent.getStringExtra(Constants.ID)
        id?.let {
            viewModel.setId(it)
        }
    }

}