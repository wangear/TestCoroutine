package com.toy.testcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.toy.testcoroutine.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding : ActivityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        var viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // this is for testing
        viewModel.setBinding(binding)

        viewModel.showText()

    }
}