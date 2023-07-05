package eshopeco.com.adapter



import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import eshopeco.com.R
import eshopeco.com.databinding.ItemCategoryBinding
import eshopeco.com.model.CategoryModel

class CategoryAdapter(var context : Context, val list: ArrayList<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){
    inner class CategoryViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var binding = ItemCategoryBinding.bind(view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
         return CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_category, parent, false) )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.categoryView.text = list[position].cate
        Glide.with(context).load(list[position].img).into(holder.binding.imageViewAd)
    }
}