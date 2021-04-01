package com.suy.bts.utils

import android.widget.EditText
import okhttp3.ResponseBody
import org.json.JSONObject

fun ResponseBody.message(): String? = JSONObject(string()).getString("message")

fun EditText.string() = text.trim().toString()

fun EditText.int() = text.trim().toString().toInt()