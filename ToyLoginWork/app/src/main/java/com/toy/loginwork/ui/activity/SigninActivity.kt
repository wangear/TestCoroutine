package com.toy.loginwork.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.toy.loginwork.LoginWorkApp
import com.toy.loginwork.R
import com.toy.loginwork.databinding.ActivitySigninBinding
import com.toy.loginwork.di.component.SigninComponent
import com.toy.loginwork.ui.view.CustomButton
import com.toy.loginwork.util.ActivityUtils
import com.toy.loginwork.viewmodel.SigninViewModel
import javax.inject.Inject


class SigninActivity : BaseActivity(), View.OnClickListener {
    @Inject
    protected lateinit var mViewModelProvider: ViewModelProvider.Factory
    lateinit var component: SigninComponent
    private lateinit var binding: ActivitySigninBinding
    private lateinit var viewModel: SigninViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setDI()
        setDatabinding()
        setViewModel()
        setTitleBar()
        initListener()
        setObserver()

    }

    private fun initListener() {
        binding.btnSignin.setOnClickListener(this)
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this, mViewModelProvider).get(SigninViewModel::class.java)
    }

    private fun setDatabinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signin)
    }

    private fun setDI() {
        component = (applicationContext as LoginWorkApp).appComponent.signinComponent().create()
        component.inject(this)
    }

    private fun setObserver() {
        viewModel.signResult.observe(this, Observer {
            if(it){
                (application as LoginWorkApp).isLogin.value = it
                startActivity(Intent(this, MainActivity::class.java))
            }
        })
        (application as LoginWorkApp).isLogin.observe(this, Observer {
            if(it)
                binding.toolbar.findViewById<CustomButton>(R.id.btn_login).visibility = View.INVISIBLE
        })
    }

    private fun setTitleBar() {
        ActivityUtils.setTitleBar(this, binding.toolbar)
    }

    override fun onClick(v: View?) {
        v?.let {
            when (it.id) {
                R.id.btn_signin -> {
                    if (checkText()) {
                        viewModel.requestSignin(
                            binding.etNickname.text.toString(),
                            binding.etIntro.text.toString(),
                            binding.etPw.text.toString()
                        )
                    }
                }

            }
        }
    }

    private fun checkText(): Boolean {
        if (TextUtils.isEmpty(binding.etNickname.text.toString())) {
            Toast.makeText(applicationContext, R.string.empty_nick, Toast.LENGTH_SHORT).show()
            return false
        }
        if (TextUtils.isEmpty(binding.etIntro.text.toString())) {
            Toast.makeText(applicationContext, R.string.empty_intro, Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}