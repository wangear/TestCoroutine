package com.toy.loginwork.ui.activity

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.toy.loginwork.LoginWorkApp
import com.toy.loginwork.R
import com.toy.loginwork.databinding.ActivityLoginBinding
import com.toy.loginwork.di.component.LoginComponent
import com.toy.loginwork.ui.view.CustomButton
import com.toy.loginwork.util.ActivityUtils
import com.toy.loginwork.viewmodel.LoginViewModel
import javax.inject.Inject


class LoginActivity : BaseActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    lateinit var component: LoginComponent
    private lateinit var viewModel: LoginViewModel

    @Inject
    protected lateinit var mViewModelProvider: ViewModelProvider.Factory

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
        binding.btnLogin.setOnClickListener(this)
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this, mViewModelProvider).get(LoginViewModel::class.java)
    }

    private fun setDatabinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    private fun setDI() {
        component = (applicationContext as LoginWorkApp).appComponent.loginComponent().create()
        component.inject(this)
    }

    private fun setObserver() {
        viewModel.loginResult.observe(this, Observer {
            if (it) {
                (application as LoginWorkApp).isLogin.value = it
                finish()
//                startActivity(Intent(this, MainActivity::class.java))
            }
        })
        (application as LoginWorkApp).isLogin.observe(this, Observer {
            if (it) {
                Log.d("wang", "ppp")
                binding.toolbar.findViewById<CustomButton>(R.id.btn_login).visibility =
                    View.INVISIBLE
            }
        })
    }

    private fun setTitleBar() {
        ActivityUtils.setTitleBar(this, binding.toolbar)
    }

    override fun onClick(v: View?) {
        v?.let {
            when (it.id) {
                R.id.btn_login -> {
                    if (checkText()) {
                        viewModel.requestLogin(
                            binding.etNickname.text.toString(),
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
        return true
    }
}