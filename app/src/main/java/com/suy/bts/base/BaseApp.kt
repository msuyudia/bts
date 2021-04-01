package com.suy.bts.base

import android.app.Application
import com.suy.bts.repository.Repository
import com.suy.bts.sharedpreference.PrefManager

class BaseApp: Application() {
    override fun onCreate() {
        Repository.initiate(this)
        PrefManager.initiate(this)
        super.onCreate()
    }
}