package com.example.vintagehaven

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter to display cart items in RecyclerView
class CartAdapter(private val products: List<Product>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    // ViewHolder class to hold views for each item
    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.productName)
        val productPrice: TextView = itemView.findViewById(R.id.productPrice)
        val productQuantity: TextView = itemView.findViewById(R.id.productQuantity)
    }

    // Called when a new view holder is created (when RecyclerView needs more items)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        // Inflate the layout for each item in the cart
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart_product, parent, false) // Define this layout for each item in the cart
        return CartViewHolder(view)
    }

    // Called to bind data to the views in the ViewHolder
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = products[position] // Get the product at the current position
        holder.productName.text = product.name // Set product name
        holder.productPrice.text = "$${product.price}" // Set product price (formatted)
        holder.productQuantity.text = "Quantity: ${product.quantity}" // Set quantity of product
    }

    // Return the number of items in the list
    override fun getItemCount(): Int {
        return products.size // Return the size of the product list
    }
}
