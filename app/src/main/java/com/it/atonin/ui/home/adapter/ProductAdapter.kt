package com.it.atonin.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.it.atonin.databinding.ItemListProductBinding
import com.it.atonin.model.Product
import com.it.atonin.utils.setImage

class ProductAdapter(
    private val onProductClick: (product: Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var data = arrayListOf<Product>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: ArrayList<Product>) {
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemListProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class ProductViewHolder(private val binding: ItemListProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            with(binding) {
                root.setOnClickListener {
                    onProductClick(product)
                }
                productImage.setImage(product.image)
                productPriceTxt.text = product.price
            }
        }
    }
}