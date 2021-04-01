package com.suy.bts.sharedpreference

import android.content.Context
import android.content.SharedPreferences

object PrefManager {
    private const val PREF_NAME = "BTS"
    private const val TOKEN_VALUE = "Token"
    private lateinit var pref: SharedPreferences

    fun initiate(context: Context) {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun getToken() = pref.getString(TOKEN_VALUE, "")
    fun saveToken(token: String) = pref.edit().putString(TOKEN_VALUE, token).apply()
    fun clear() = pref.edit().clear().apply()
}