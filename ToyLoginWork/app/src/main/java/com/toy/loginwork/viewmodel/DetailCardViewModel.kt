package com.toy.loginwork.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toy.loginwork.repository.Repository
import com.toy.loginwork.repository.data.CardDetailData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailCardViewModel @Inject constructor(private var repository: Repository) : ViewModel() {
    private val _card = MutableLiveData<CardDetailData>()
    val card: LiveData<CardDetailData>
        get() = _card

    private var cardId : String? = null

    fun requestCardDetail() {
        cardId?.let{
            viewModelScope.launch {
                reqCardDetail(it)
            }
        }
    }

    private suspend fun reqCardDetail(id: String) {
        withContext(Dispatchers.IO) {
            val response = repository.requestCardDetail(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    if (it.success)
                        _card.postValue(it)
                }
            }
        }
    }

    fun setId(id : String){
        cardId = id
    }

}