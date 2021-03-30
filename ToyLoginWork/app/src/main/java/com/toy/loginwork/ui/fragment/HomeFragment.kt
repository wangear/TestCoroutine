package com.toy.loginwork.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.toy.loginwork.R
import com.toy.loginwork.databinding.FragmentHomeBinding
import com.toy.loginwork.repository.data.PopularCardData
import com.toy.loginwork.repository.data.PopularUserData
import com.toy.loginwork.ui.adapter.PopCardListAdapter
import com.toy.loginwork.ui.adapter.PopUserListAdapter
import com.toy.loginwork.viewmodel.MainViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val sharedViewModel: MainViewModel by activityViewModels()

    fun HomeFragment() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        setRefreshListener()

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
    }

    private fun setObserver() {
        sharedViewModel.popCardList.observe(viewLifecycleOwner, Observer { list ->
            if (binding.rvCard.adapter == null) {
                initCardAdapater(list)
            } else {
                binding.rvCard.adapter?.notifyDataSetChanged()
                binding.slHome.isRefreshing = false

            }
        })
        sharedViewModel.popUserList.observe(viewLifecycleOwner, Observer { list ->
            if (binding.rvUser.adapter == null) {
                initUserAdapater(list)
            } else {
                binding.rvUser.adapter?.notifyDataSetChanged()
            }
        })
    }

    private fun setRefreshListener() {
        binding.slHome.setOnRefreshListener {
            sharedViewModel.requestHomeData()
        }
    }

    private fun initCardAdapater(list: List<PopularCardData>) {
        binding.rvCard.adapter = PopCardListAdapter(list)
    }

    private fun initUserAdapater(list: List<PopularUserData>) {
        binding.rvUser.adapter = PopUserListAdapter(list)
    }
}