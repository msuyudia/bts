package com.suy.bts.model.response

data class ListResponse<T>(
    val statusCode: Int? = null,
    val message: String? = null,
    val errorMessage: String? = null,
    val data: List<T>? = null
)
