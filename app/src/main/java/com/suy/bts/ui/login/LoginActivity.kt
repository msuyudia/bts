package com.suy.bts.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.afollestad.vvalidator.form
import com.readystatesoftware.chuck.internal.ui.MainActivity
import com.suy.bts.base.BaseActivity
import com.suy.bts.databinding.ActivityLoginBinding
import com.suy.bts.ui.home.HomeActivity
import com.suy.bts.ui.register.RegisterActivity
import com.suy.bts.utils.Status
import com.suy.bts.utils.string
import splitties.activities.start
import splitties.toast.longToast

class LoginActivity:  BaseActivity(){

    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initiateViewModel()
        initiateForm()
        initiateListener()
    }

    private fun initiateViewModel() {
        viewModel.liveData().observe(this, {
            when (it.status) {
                Status.LOADING -> showLoading
                Status.SUCCESS -> {
                    hideLoading
                    longToast(it.data ?: "")
                    start<HomeActivity>{
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }
                }
                Status.ERROR -> {
                    hideLoading
                    longToast(it.message ?: "")
                }
            }
        })
    }

    private fun initiateListener() {
        binding.btnRegister.setOnClickListener { start<RegisterActivity>() }
    }

    private fun initiateForm() {
        form {
            with(binding) {
                input(etUsername) {
                    isNotEmpty().description("Username can't be empty")
                }
                input(etPassword) {
                    isNotEmpty().description("Password can't be empty")
                }
                submitWith(btnLogin) {
                    viewModel.login(etUsername.string(), etPassword.string())
                }
            }
        }
    }
}