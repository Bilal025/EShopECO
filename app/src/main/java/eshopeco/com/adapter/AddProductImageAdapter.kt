package eshopeco.com.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eshopeco.com.databinding.ActivityAddProductBinding
import eshopeco.com.databinding.ImageItemBinding
import eshopeco.com.model.ProductModel

class AddProductImageAdapter (val list : ArrayList<Uri>): RecyclerView.Adapter<AddProductImageAdapter.AddProductImageViewHolder>(){

    inner class AddProductImageViewHolder(val binding: ImageItemBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddProductImageViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddProductImageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AddProductImageViewHolder, position: Int) {
        holder.binding.itemImage.setImageURI(list[position])
    }

}