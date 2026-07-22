package com.example.sanguosuoclient.data.model

import com.example.sanguosuoclient.ui.screen.signin.SignInUiState
import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(
    val username: String,
    val password: String
)

@Serializable
data class SignInData(
    val userID: String,
    val sessionToken: String
)

@Serializable
data class SignInPayload(
    val message: String,
    val data: SignInData
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
