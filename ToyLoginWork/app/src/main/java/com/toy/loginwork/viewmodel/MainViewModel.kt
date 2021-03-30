package com.toy.loginwork.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toy.loginwork.repository.Repository
import com.toy.loginwork.repository.data.PopularCardData
import com.toy.loginwork.repository.data.PopularUserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(private var repository: Repository) : ViewModel() {
    private val _popCardList = MutableLiveData<List<PopularCardData>>()
    val popCardList: LiveData<List<PopularCardData>>
        get() = _popCardList

    private val _popUserList = MutableLiveData<List<PopularUserData>>()
    val popUserList: LiveData<List<PopularUserData>>
        get() = _popUserList

    private val _picCardList = MutableLiveData<List<PopularCardData>>()
    val picCardList: LiveData<List<PopularCardData>>
        get() = _picCardList

    init {
        viewModelScope.launch {
            reqHomeData()
            reqInitPictureData()
        }
    }

    fun requestHomeData() {
        viewModelScope.launch {
            reqHomeData()
        }
    }

    fun requestInitPictureData() {
        viewModelScope.launch {
            reqInitPictureData()
        }
    }

    fun requestPictureData(page: Int) {
        viewModelScope.launch {
            reqPictureData(page)
        }
    }

    private suspend fun reqHomeData() {
        withContext(Dispatchers.IO) {
            var response = repository.requestHomeData()
            if (response.isSuccessful) {
                response.body()?.let {
                    _popCardList.postValue(it.popularCards)
                    _popUserList.postValue(it.popularUsers)
                }
            } else {
                // 요청 실패
            }
        }
    }

    private suspend fun reqInitPictureData() {
        withContext(Dispatchers.IO) {
            var response = repository.requestPictures(1, Repository.PER)
            if (response.isSuccessful) {
                response.body()?.let {
                    _picCardList.postValue(it.popularCards)
                }
            } else {
                // 요청 실패
            }
        }
    }

    private suspend fun reqPictureData(page: Int) {
        withContext(Dispatchers.IO) {
            var response = repository.requestPictures(page, Repository.PER)
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d("wang", "b size = ${_picCardList.value!!.size}")
                    _picCardList.value?.let { value ->
                        var temp: List<PopularCardData> = value.plus(it.popularCards)
                        _picCardList.postValue(temp)
                        Log.d("wang", "a temp size = ${temp.size}")
                        Log.d("wang", "a size = ${_picCardList.value!!.size}")
                    }
                }
            } else {
                // 요청 실패
            }

        }
    }
}
