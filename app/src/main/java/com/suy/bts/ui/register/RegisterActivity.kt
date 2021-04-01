package com.suy.bts.ui.register

import android.os.Bundle
import androidx.activity.viewModels
import com.afollestad.vvalidator.form
import com.suy.bts.base.BaseActivity
import com.suy.bts.databinding.ActivityRegisterBinding
import com.suy.bts.utils.Status
import com.suy.bts.utils.string
import splitties.activities.start
import splitties.toast.longToast

class RegisterActivity: BaseActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initiateViewModel()
        initiateForm()
    }

    private fun initiateViewModel() {
        viewModel.liveData().observe(this, {
            when (it.status) {
                Status.LOADING -> showLoading
                Status.SUCCESS -> {
                    hideLoading
                    longToast(it.data ?: "")
                    onBackPressed()
                }
                Status.ERROR -> {
                    hideLoading
                    longToast(it.message ?: "")
                }
            }
        })
    }

    private fun initiateForm() {
        form {
            with(binding) {
                input(etEmail) {
                    isNotEmpty().description("Email can't be empty")
                    isEmail().description("Invalid email")
                }
                input(etUsername) {
                    isNotEmpty().description("Username can't be empty")
                }
                input(etPassword) {
                    isNotEmpty().description("Password can't be empty")
                }
                submitWith(btnRegister) {
                    viewModel.register(etEmail.string(), etUsername.string(), etPassword.string())
                }
            }
        }
    }
}