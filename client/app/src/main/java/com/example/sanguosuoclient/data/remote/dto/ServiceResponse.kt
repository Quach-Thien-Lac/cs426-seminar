package com.example.sanguosuoclient.data.remote.dto

data class ServiceResponse<T>(
    val success: Boolean,
    val statusCode: Int,
    val payload: T?
)
