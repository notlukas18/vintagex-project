package com.example.vintagehaven

data class ProductResponse(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val quantity: Int
)