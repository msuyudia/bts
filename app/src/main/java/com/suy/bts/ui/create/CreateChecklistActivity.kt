package com.suy.bts.ui.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.suy.bts.databinding.ActivityCreateChecklistBinding

class CreateChecklistActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateChecklistBinding
    private val viewModel by viewModels<CreateChecklistViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateChecklistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initiateViewModel()
    }

    private fun initiateViewModel() {

    }
}