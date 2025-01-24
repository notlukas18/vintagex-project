package com.example.vintagehaven


object Cart {
    // List to store products added to the cart
    val products = mutableListOf<Product>()

    // Method to add a product to the cart
    fun addProductToCart(product: Product) {
        // Check if product already exists in the cart
        val existingProduct = products.find { it.name == product.name }
        if (existingProduct != null) {
            // If product exists, increase the quantity
            existingProduct.quantity += product.quantity
        } else {
            // If product doesn't exist, add the new product
            products.add(product)
        }
    }

    // Method to remove a product from the cart
    fun removeProductFromCart(product: Product) {
        // Remove the product from the cart
        products.remove(product)
    }

    // Method to get the total price of all products in the cart
    fun getTotalPrice(): Double {
        var totalPrice = 0.0
        for (product in products) {
            // Calculate total price based on quantity and price
            totalPrice += product.price.toDouble() * product.quantity
        }
        return totalPrice
    }

    // Method to clear all products in the cart
    fun clearCart() {
        products.clear()
    }
}