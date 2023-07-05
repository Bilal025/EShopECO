package eshopeco.com.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import eshopeco.com.ProductDetailActivity
import eshopeco.com.databinding.CategoryProductItemBinding
import eshopeco.com.databinding.ItemCategoryBinding
import eshopeco.com.databinding.LayoutProductItemBinding
import eshopeco.com.model.AddProductModel
import eshopeco.com.model.ProductModel

class CategoryProductAdapter(val context: Context, val list: ArrayList<AddProductModel>)
    : Adapter<CategoryProductAdapter.CategoryProductViewHolder>(){

    inner class CategoryProductViewHolder(val binding: CategoryProductItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryProductViewHolder {
        val binding = CategoryProductItemBinding.inflate(LayoutInflater.from(context), parent,false)
        return CategoryProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CategoryProductViewHolder, position: Int) {
        Glide.with(context).load(list[position].productCoverImg).into(holder.binding.cToPimageView)
        holder.binding.cToPtextView2.text = list[position].productName
        holder.binding.cToPtextView.text = list[position].productSp

        holder.itemView.setOnClickListener {
            val intent= Intent(context, ProductDetailActivity::class.java)
            intent.putExtra("id", list[position].productId)
            context.startActivity(intent)
        }
    }

}