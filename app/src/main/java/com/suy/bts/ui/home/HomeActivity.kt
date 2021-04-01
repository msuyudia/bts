package com.suy.bts.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import com.suy.bts.base.BaseActivity
import com.suy.bts.databinding.ActivityHomeBinding
import com.suy.bts.ui.create.CreateChecklistActivity
import com.suy.bts.utils.Status
import splitties.activities.start
import splitties.toast.longToast

class HomeActivity : BaseActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel by viewModels<HomeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initiateViewModel()
        initiateListener()
    }

    private fun initiateViewModel() {
        viewModel.getChecklist()
        viewModel.liveData().observe(this, {
            when (it.status) {
                Status.LOADING -> showLoading
                Status.SUCCESS -> {
                    hideLoading
                    binding.rv.adapter = ChecklistAdapter(it.data ?: mutableListOf())
                }
                Status.ERROR -> {
                    hideLoading
                    longToast(it.message ?: "")
                }
            }
        })
    }

    private fun initiateListener() {
        binding.fab.setOnClickListener { start<CreateChecklistActivity>() }
    }
}