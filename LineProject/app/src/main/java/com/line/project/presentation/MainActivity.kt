package com.line.project.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.line.project.Constants
import com.line.project.R
import com.line.project.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initObject()
        initObserver()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPerm()
        } else {
            viewModel.parsingData(Constants.DATA_API)
        }


    }

    private fun initObject() {
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.ivImg.setOnClickListener(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkPerm() {
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                Constants.PERM_REQ_CODE
            )
        } else {
            viewModel.parsingData(Constants.DATA_API)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.PERM_REQ_CODE) {
            when (PackageManager.PERMISSION_GRANTED) {
                grantResults[0] -> {
                    viewModel.parsingData(Constants.DATA_API)
                }
                else -> {
                    finish()
                }
            }
        }
    }

    private fun initObserver() {
        viewModel.imgLiveData.observe(this, Observer {
            viewModel.downLoadingLiveData.value = false
            binding.ivImg.setImageBitmap(it)
        })
        viewModel.downLoadingLiveData.observe(this, Observer {
            if (it) {
                binding.ivImg.visibility = View.GONE
                binding.tvProgress.visibility = View.VISIBLE
            } else {
                binding.ivImg.visibility = View.VISIBLE
                binding.tvProgress.visibility = View.GONE
            }
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_img -> {
                CoroutineScope(Dispatchers.Main).launch { viewModel.refresh() }
            }
        }
    }
}