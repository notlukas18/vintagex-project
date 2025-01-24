package com.example.vintagehaven

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private val productList = mutableListOf<Product>() // List to hold products

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Set up RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Initialize the adapter with a click listener
        productAdapter = ProductAdapter(productList) { selectedProduct ->
            // Handle item click

            // Set default quantity value (e.g., 1 for new users)
            val quantity = 1 // You can update this with the actual quantity logic

            // Create ProductDetailFragment instance
            val productDetailFragment = ProductDetailFragment()

            // Pass product ID and quantity as arguments
            val bundle = Bundle().apply {
                putInt("PRODUCT_ID", selectedProduct.id)
                putInt("QUANTITY", quantity) // Pass quantity here
            }

            productDetailFragment.arguments = bundle

            // Replace the current fragment with the ProductDetailFragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, productDetailFragment)
                .addToBackStack(null) // Add to back stack to allow back navigation
                .commit()
        }

        recyclerView.adapter = productAdapter

        // Load products from API
        loadProductsFromApi()

        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadProductsFromApi() {
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getProducts()
                productList.clear()
                productList.addAll(response.map { apiResponse ->
                    Product(
                        id = apiResponse.id,
                        name = apiResponse.name,
                        description = apiResponse.description,
                        price = apiResponse.price,
                        imageResId = R.drawable.placeholder_image, // Placeholder image, update with actual image loading logic
                        quantity = apiResponse.quantity
                    )
                })
                productAdapter.notifyDataSetChanged()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}