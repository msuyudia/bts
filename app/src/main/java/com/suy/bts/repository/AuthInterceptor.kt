package com.suy.bts.repository

import com.suy.bts.sharedpreference.PrefManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authRequest = request.newBuilder()
            .addHeader("Authorization", "Bearer ${PrefManager.getToken()}")
            .addHeader("Content-Type", "application/json")
            .build()
        return chain.proceed(authRequest)
    }
}