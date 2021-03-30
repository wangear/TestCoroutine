package com.toy.loginwork.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toy.loginwork.repository.Repository
import com.toy.loginwork.repository.data.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SigninViewModel @Inject constructor(private var repository: Repository) : ViewModel() {
    private val _signResult = MutableLiveData<Boolean>()
    val signResult: LiveData<Boolean>
        get() = _signResult

    fun requestSignin(nickname: String, intro: String, pwd: String) {
        viewModelScope.launch {
            var userData = UserData(nickname, intro, pwd)
            requestSign(userData)
        }
    }

    private suspend fun requestSign(userData: UserData) {
        withContext(Dispatchers.IO) {
            val response = repository.requestSign(userData)
            if (response.isSuccessful) {
                _signResult.postValue(true)
            }
        }
    }

}