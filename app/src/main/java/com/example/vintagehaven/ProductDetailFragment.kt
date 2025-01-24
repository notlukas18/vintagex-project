package com.example.vintagehaven

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProductDetailFragment : Fragment() {

    private lateinit var productImage: ImageView
    private lateinit var productName: TextView
    private lateinit var productDescription: TextView
    private lateinit var productPrice: TextView
    private lateinit var productQuantity: TextView // TextView to display quantity

    private var currentProductId: Int? = null
    private var quantity: Int = 1 // Default quantity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the views
        productImage = view.findViewById(R.id.productImage)
        productName = view.findViewById(R.id.productName)
        productDescription = view.findViewById(R.id.productDescription)
        productPrice = view.findViewById(R.id.productPrice)
        productQuantity = view.findViewById(R.id.productQuantity) // Initialize quantity TextView

        // Get product ID and quantity from the arguments
        val productId = arguments?.getInt("PRODUCT_ID")
        quantity = arguments?.getInt("QUANTITY", 1) ?: 1 // Default to 1 if quantity is not passed

        if (productId == null) {
            Log.e("ProductDetailFragment", "No product ID passed in the arguments")
            return
        }

        // Only load the product if the ID has changed or if it's the first time
        if (productId != currentProductId) {
            currentProductId = productId
            loadProductDetails(productId)
        }
    }

    private fun loadProductDetails(productId: Int) {
        // Example product data with quantity
        val exampleProducts = listOf(
            Product(1, "Vintage Dress", "A beautiful vintage dress.", 49.99, R.drawable.vintage_dress, 1),
            Product(2, "Antique Vase", "A retro-style vase.", 29.99, R.drawable.antique_vase, 1),
            Product(3, "Antique Chair", "An antique wooden chair.", 89.99, R.drawable.chair, 1)
        )

        // Find product by ID
        val product = exampleProducts.find { it.id == productId }

        if (product != null) {
            // Set product details
            productImage.setImageResource(product.imageResId)
            productName.text = product.name
            productDescription.text = product.description
            productPrice.text = "$${product.price}"

            // Display the quantity
            productQuantity.text = "Quantity: $quantity"
        } else {
            // Handle case when product is not found in the list
            Log.e("ProductDetailFragment", "Product not found with ID: $productId")
            productName.text = "Product not found"
            productDescription.text = "Sorry, we couldn't find the details for this product."
            productPrice.text = ""
            productImage.setImageResource(R.drawable.placeholder_image) // Placeholder image
            productQuantity.text = "Quantity: 0"
        }
    }
}
