package com.example.sanguosuoclient.data.model

import kotlinx.serialization.Serializable

data class SignInRequest(
    val email: String,
    val password: String
)

data class SignInResponse(
    val token: String,
    val user: User
)

@Serializable
data class SignUpRequest(
    val name: String,
    val username: String,
    val email: String,
    val password: String,
    val phone: String
)

@Serializable
data class User(
    val userID: String,
    val username: String,
)


@Serializable
data class SignUpPayload(
    val message: String,
    val data: User
)
