package com.toy.loginwork.ui.activity

import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0);
    }
}