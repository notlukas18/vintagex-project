package com.example.vintagehaven

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide // Optional: If you're loading images from URLs

class ProductAdapter(
    private val productList: List<Product>,  // List of products
    private val onItemClick: (Product) -> Unit // Lambda function to handle item clicks
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    // Create a ViewHolder for each item in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        // Inflate the product item layout
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    // Bind the data to the ViewHolder
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    // Return the size of the product list
    override fun getItemCount(): Int {
        return productList.size
    }

    // ViewHolder class to hold and bind the data for a single product item
    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productImage: ImageView = itemView.findViewById(R.id.productImage)
        private val productName: TextView = itemView.findViewById(R.id.productName)
        private val productPrice: TextView = itemView.findViewById(R.id.productPrice)

        // Bind data to the views
        fun bind(product: Product) {
            // If you're loading images dynamically, use Glide or Picasso here:
            Glide.with(itemView.context)
                .load(product.imageResId) // Or use product.imageUrl if you have a URL
                .into(productImage)  // Set the product image

            productName.text = product.name  // Set the product name
            productPrice.text = "$${product.price}"  // Set the product price (formatted)

            // Set up click listener on the entire item view
            itemView.setOnClickListener {
                onItemClick(product)  // Trigger the onItemClick listener passed from HomeFragment
            }
        }
    }
}
