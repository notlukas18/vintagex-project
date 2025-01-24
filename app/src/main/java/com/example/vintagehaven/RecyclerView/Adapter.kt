package com.example.vintagehaven.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vintagehaven.Product
import com.example.vintagehaven.R

class ProductAdapter(private val productList: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.productName)
        val productPrice: TextView = itemView.findViewById(R.id.productPrice)
        val productImage: ImageView = itemView.findViewById(R.id.productImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.productName.text = product.name
        holder.productPrice.text = "$${product.price}"
        holder.productImage.setImageResource(product.imageResId)
        // Load image using a library like Glide or Picasso
        Glide.with(holder.itemView.context)
            .load(product.imageResId)
            .into(holder.productImage)
    }

    override fun getItemCount() = productList.size
}