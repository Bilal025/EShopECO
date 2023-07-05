package eshopeco.com.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import eshopeco.com.databinding.LayoutCartItemBinding
import eshopeco.com.fragment.CartFragment
import eshopeco.com.model.CartModel

class CartAdapter (private  val context: Context,private val list: ArrayList<CartModel>)
    :RecyclerView.Adapter<CartAdapter.ViewHolder>(){

        inner class ViewHolder(val binding:LayoutCartItemBinding) : RecyclerView.ViewHolder(binding.root){
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutCartItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false))
    }


    override fun getItemCount(): Int {
        return list.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]

        Glide.with(context).load(currentItem.imageUrl).into(holder.binding.CartProductView)

        holder.binding.pName.text = currentItem.productName
        holder.binding.pPrice.text = currentItem.productPrice

    }
}