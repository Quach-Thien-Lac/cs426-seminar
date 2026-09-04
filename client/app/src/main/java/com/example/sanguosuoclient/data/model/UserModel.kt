package com.example.sanguosuoclient.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    @SerialName("user_id")
    val userId: String,
    @SerialName("user_username")
    val username: String,
    @SerialName("user_email")
    val email: String,
    @SerialName("user_phone")
    val phone: String,
)

@Serializable
data class UserInfoPayload(
    val message: String,
    val data: UserInfo
)