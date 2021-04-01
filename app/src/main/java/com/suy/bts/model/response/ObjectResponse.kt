package com.suy.bts.model.response

data class ObjectResponse<T>(
    val statusCode: Int? = null,
    val message: String? = null,
    val errorMessage: String? = null,
    val data: T? = null
)
