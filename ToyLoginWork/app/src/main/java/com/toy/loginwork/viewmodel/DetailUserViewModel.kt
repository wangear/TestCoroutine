package com.toy.loginwork.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toy.loginwork.repository.Repository
import com.toy.loginwork.repository.data.PopularUserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailUserViewModel @Inject constructor(private var repository: Repository) : ViewModel() {
    private val _user = MutableLiveData<PopularUserData>()
    val user: LiveData<PopularUserData>
        get() = _user
    private var userId: String? = null

    fun requestUserDetail() {
        userId?.let {
            viewModelScope.launch {
                reqUserDetail(it)
            }
        }
    }

    private suspend fun reqUserDetail(id: String) {
        withContext(Dispatchers.IO) {
            val response = repository.requestUserDetail(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    if (it.success)
                        _user.postValue(it.user)
                }
            }
        }
    }

    fun setId(id: String) {
        userId = id
    }
}