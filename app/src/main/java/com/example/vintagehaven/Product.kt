package com.example.vintagehaven

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageResId: Int,
    var quantity: Int // Change from val to var
)
