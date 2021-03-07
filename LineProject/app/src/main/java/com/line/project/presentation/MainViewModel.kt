package com.line.project.presentation

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.line.project.Constants
import com.line.project.data.MovieData
import com.line.project.domain.usecase.GetImageUseCase
import kotlinx.coroutines.*
import org.json.JSONObject

class MainViewModel : ViewModel() {

    var titleLiveData: MutableLiveData<String> = MutableLiveData()
    var imgLiveData: MutableLiveData<Bitmap> = MutableLiveData()
    var imgProgessLiveData: MutableLiveData<String> = MutableLiveData()
    var downLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    private var pos = 0
    private var movieData: MovieData? = null

    private var getImageUseCase: GetImageUseCase = GetImageUseCase()

    fun parsingData(dataapi: String) {
        viewModelScope.launch(Dispatchers.IO) {
            var jsonObj = JSONObject(dataapi)
            var title = jsonObj.getString(Constants.TITLE)
            var jsonList = jsonObj.getJSONArray(Constants.IMAGE)
            var imgUrlList = ArrayList<String>()
            for (i in 0 until jsonList.length()) {
                imgUrlList.add(jsonList.getString(i))
            }

            movieData = MovieData(title, imgUrlList)
            movieData?.let {
                titleLiveData.postValue(it.title)
                refresh()
            }
        }
    }

    suspend fun getDownloadImg(url: String) {
        downLoadingLiveData.postValue(true)
        val result: Deferred<Bitmap?> = viewModelScope.async(Dispatchers.IO) {
            var bitmap = getImageUseCase.downloadImage(url, movieData?.title + pos)
//            cache.put(movieData?.title + pos, bitmap)
            return@async bitmap
        }

        viewModelScope.launch {
            while (result.isActive) {
                imgProgessLiveData.postValue((getImageUseCase.getContentSize() / 1000).toString() + "kb downloading")
                delay(100)
            }
        }
        imgLiveData.postValue(result.await())
        downLoadingLiveData.postValue(false)
        pos++
    }

    suspend fun refresh() {
        movieData?.let {
            if (it.image.isEmpty())
                return
            if (pos >= it.image.size) {
                pos = 0
            }
            viewModelScope.launch {
                var imgBitmap = getImageUseCase.getImage(it.title + pos)

                if (imgBitmap == null)
                    getDownloadImg(it.image[pos])
                else {
                    imgLiveData.value = imgBitmap
                    pos++;
                }
            }
        }
    }
}