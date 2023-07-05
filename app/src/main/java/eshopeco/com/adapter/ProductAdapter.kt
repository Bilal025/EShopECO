package eshopeco.com.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import eshopeco.com.databinding.LayoutProductItemBinding
import eshopeco.com.model.ProductModel


class ProductAdapter(val context: Context, val list: ArrayList<ProductModel>)
    : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: LayoutProductItemBinding)
        : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = LayoutProductItemBinding.inflate(LayoutInflater.from(context), parent,false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val data = list[position]

        Glide.with(context).load(data.productCoverImg).into(holder.binding.productimageView)
        holder.binding.productTextView.text = data.productName
        holder.binding.catTextView.text = data.productCategory
        holder.binding.priceTextView.text = "Rs. ${data.productPrice}"



    }

    override fun getItemCount(): Int {
        return list.size
    }

}

