package eshopeco.com.adapter



import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import eshopeco.com.CategoryProductsActivity
import eshopeco.com.R
import eshopeco.com.databinding.LayoutCategoryItemBinding
import eshopeco.com.model.CategoryModel

class CategoryAdapterUser(var context : Context, val list: ArrayList<CategoryModel>) : RecyclerView.Adapter<CategoryAdapterUser.CategoryViewHolder>(){
    inner class CategoryViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var binding = LayoutCategoryItemBinding.bind(view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
         return CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_category_item, parent, false) )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.categoryTextView.text = list[position].cate
        Glide.with(context).load(list[position].img).into(holder.binding.categoryImageView)

        holder.itemView.setOnClickListener{
            val intent = Intent(context, CategoryProductsActivity::class.java)
            intent.putExtra("cate", list[position].cate)
            context.startActivity(intent)
        }


    }
}