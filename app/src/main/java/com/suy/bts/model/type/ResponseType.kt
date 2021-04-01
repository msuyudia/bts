package com.suy.bts.model.type

enum class ResponseType(val code: Int) {
    SUCCESS(200), CREATED(201), UNAUTHORIZED(401), FORBIDDEN(403), NOT_FOUND(404)
}