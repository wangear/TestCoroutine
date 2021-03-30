package com.toy.loginwork.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toy.loginwork.repository.Repository
import com.toy.loginwork.repository.data.UserWithoutIns
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginViewModel @Inject constructor(private var repository: Repository) : ViewModel() {
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean>
        get() = _loginResult

    fun requestLogin(nickname: String, pwd: String) {
        //    request_json_body : { "nicknme": "닉네임", "introduction": "소개", "pwd": "패스워드" }
        viewModelScope.launch {
            var userData = UserWithoutIns(nickname, pwd)
            requestLogin(userData)
        }
    }

    private suspend fun requestLogin(userData: UserWithoutIns) {
        withContext(Dispatchers.IO) {
            val response = repository.requestLogin(userData)
            if (response.isSuccessful) {
                _loginResult.postValue(true)
            }
        }
    }

}