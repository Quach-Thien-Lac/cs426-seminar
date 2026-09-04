package com.example.sanguosuoclient.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val userId: String,
    val username: String,
    val email: String,
    val phone: String,
)