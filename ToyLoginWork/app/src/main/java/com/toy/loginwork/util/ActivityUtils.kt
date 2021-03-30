package com.toy.loginwork.util

import android.content.Intent
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.toy.loginwork.R
import com.toy.loginwork.ui.view.CustomButton
import com.toy.loginwork.ui.activity.LoginActivity
import com.toy.loginwork.ui.activity.SigninActivity

class ActivityUtils {
    companion object {
        fun setTitleBar(act: AppCompatActivity, toolbar: Toolbar) {
            act.setSupportActionBar(toolbar)
            act.supportActionBar?.setDisplayShowCustomEnabled(true)
            act.supportActionBar?.setDisplayShowTitleEnabled(false)
            var inflater: LayoutInflater =
                act.getSystemService(AppCompatActivity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var actionbarView: View = inflater.inflate(R.layout.layout_title, null)
            act.supportActionBar?.setCustomView(
                actionbarView,
                ActionBar.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER
                )
            )

            actionbarView.findViewById<CustomButton>(R.id.btn_login).setOnClickListener {
                act.startActivity(Intent(act, LoginActivity::class.java))
                act.overridePendingTransition(0,0)
            }

            actionbarView.findViewById<CustomButton>(R.id.btn_signin).setOnClickListener {
                act.startActivity(Intent(act, SigninActivity::class.java))
                act.overridePendingTransition(0,0)
            }
        }
    }

}