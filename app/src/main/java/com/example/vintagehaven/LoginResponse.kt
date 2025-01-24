package com.example.vintagehaven

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val token: String? // Assuming the response includes a token
)
data class ProfileResponse(
    val userName: String,
    val userPhone: String
)
data class RegisterResponse(
    val success: Boolean,
    val message: String
)