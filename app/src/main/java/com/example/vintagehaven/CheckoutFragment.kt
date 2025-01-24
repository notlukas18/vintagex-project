package com.example.vintagehaven

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CheckoutFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var totalPriceTextView: TextView
    private lateinit var clearCartButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_checkout, container, false)

        // Initialize views
        recyclerView = view.findViewById(R.id.recyclerView)
        totalPriceTextView = view.findViewById(R.id.totalPriceTextView)
        clearCartButton = view.findViewById(R.id.clearCartButton)

        // Set up RecyclerView with CartAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        val cartAdapter = CartAdapter(Cart.products)
        recyclerView.adapter = cartAdapter

        // Display total price of products in the cart
        updateTotalPrice()

        // Clear cart button
        clearCartButton.setOnClickListener {
            Cart.clearCart()  // Clear all products in the cart
            cartAdapter.notifyDataSetChanged()  // Notify adapter to refresh the RecyclerView
            updateTotalPrice()  // Update the total price display
        }

        return view
    }

    // Method to update total price display
    private fun updateTotalPrice() {
        val totalPrice = Cart.getTotalPrice()
        totalPriceTextView.text = "Total: $${"%.2f".format(totalPrice)}"
    }
}
