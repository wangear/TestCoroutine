package com.toy.testcoroutine

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toy.testcoroutine.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainViewModel : ViewModel(){
    var text : String? = null
    private lateinit var binding : ActivityMainBinding

    companion object {
        const val TAG = "WANG"
    }

    fun showText(){
        Log.d(TAG,"start")
        viewModelScope.launch {
            var job = async {
                text = getTextFromRepo()
            }
            async {
                showTextInView()
                if(text == null){
                    job.await()
                    Log.d(TAG,"text await = $text")
                    binding.tvText.text = text
                }
            }
        }
    }

    private suspend fun getTextFromRepo() : String{
        withContext(Dispatchers.IO) {
            delay(4000)
        }
        return "4 sec later"
    }

    private suspend fun showTextInView() {
        delay(3000)
        Log.d(TAG,"text = $text")
    }

    fun setBinding(binding:ActivityMainBinding) {
        this.binding = binding
    }


}