package com.suy.bts.repository

import android.annotation.SuppressLint
import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.suy.bts.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

@SuppressLint("StaticFieldLeak")
object Repository {
    private lateinit var context: Context
    fun initiate(context: Context) {
        this.context = context
    }

    private val interceptorLogin by lazy {
        OkHttpClient.Builder().readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(ChuckInterceptor(context))
            .build()
    }

    private val interceptor by lazy {
        OkHttpClient.Builder().readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(ChuckInterceptor(context))
            .addInterceptor(AuthInterceptor())
            .build()
    }


    private val connection by lazy {
        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .client(interceptor)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
    }

    private val connectionLogin by lazy {
        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .client(interceptorLogin)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
    }

    val api: RepoService by lazy { connection.create(RepoService::class.java) }
    val apiLoginRegister: RepoService by lazy { connectionLogin.create(RepoService::class.java) }

}