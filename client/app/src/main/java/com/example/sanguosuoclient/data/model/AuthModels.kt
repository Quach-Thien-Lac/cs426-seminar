package com.example.sanguosuoclient.data.model

data class SignInRequest(
    val username: String,
    val password: String
)

data class SignUpRequest(
    val username: String,
    val email: String,
    val password: String
)

data class SignInResponse(
    val token: String,
    val user: User
)

data class User(
    val id: String,
    val username: String,
    val email: String
)
