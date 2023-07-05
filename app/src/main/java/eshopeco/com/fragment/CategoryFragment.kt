package eshopeco.com.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import eshopeco.com.R
import eshopeco.com.adapter.CategoryAdapter
import eshopeco.com.adapter.CategoryAdapterUser
import eshopeco.com.databinding.FragmentCategoryBinding
import eshopeco.com.model.CategoryModel
import java.util.ArrayList


class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(layoutInflater)

        getCategories()
        return binding.root


    }

    private fun getCategories() {
        val list = ArrayList<CategoryModel>()
        Firebase.firestore.collection("categories")
            .get().addOnSuccessListener {
                list.clear()
                for (doc in it.documents){
                    val data = doc.toObject(CategoryModel::class.java)
                    list.add(data!!)
                }
                binding.categoryRecy.adapter = CategoryAdapterUser(requireContext(),list)
            }
    }


}