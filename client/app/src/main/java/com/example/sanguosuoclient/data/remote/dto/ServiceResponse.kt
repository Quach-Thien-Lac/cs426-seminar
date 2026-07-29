package com.example.sanguosuoclient.data.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class ServiceResponse<T>(
    val success: Boolean,
    val statusCode: Int,
    val payload: T?
)
