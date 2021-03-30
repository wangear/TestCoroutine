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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.toy.loginwork.R
import com.toy.loginwork.databinding.FragmentPictureBinding
import com.toy.loginwork.repository.Repository
import com.toy.loginwork.repository.data.PopularCardData
import com.toy.loginwork.ui.adapter.PictureListAdapter
import com.toy.loginwork.viewmodel.MainViewModel

class PictureFragment : Fragment() {

    private lateinit var binding: FragmentPictureBinding
    private val sharedViewModel: MainViewModel by activityViewModels()
    private var isLoading = false

    fun PictureFragment() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_picture, container, false)

        setRefreshListener()
        setScrollListener()

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
    }

    private fun initPictureAdapater(list: List<PopularCardData>) {
        binding.rvPicture.adapter = PictureListAdapter(list)
    }

    private fun loadMore(size: Int) {
        var page = size / Repository.PER
        sharedViewModel.requestPictureData(page + 1)
    }

    private fun setObserver() {
        sharedViewModel.picCardList.observe(viewLifecycleOwner, Observer { list ->
            if (binding.rvPicture.adapter == null) {
                initPictureAdapater(list)
            } else {
                (binding.rvPicture.adapter!! as PictureListAdapter).updateData(list)
                binding.rvPicture.adapter!!.notifyDataSetChanged()
                binding.slPicture.isRefreshing = false
                isLoading = false
            }
        })
    }

    private fun setScrollListener() {
        binding.rvPicture.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    var size = sharedViewModel.picCardList.value?.size ?: 0
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == (size - 1)) {
                        //bottom of list!
                        loadMore(size)
                        isLoading = true
                    }
                }
            }
        })
    }

    private fun setRefreshListener() {
        binding.slPicture.setOnRefreshListener {
            sharedViewModel.requestInitPictureData()
        }
    }
}