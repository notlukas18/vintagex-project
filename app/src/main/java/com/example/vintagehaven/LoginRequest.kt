package com.example.vintagehaven

data class LoginRequest(
    val email: String,
    val password: String
)
data class RegisterRequest(
    val name: String,
    val phoneNumber: String,
    val password: String
)
