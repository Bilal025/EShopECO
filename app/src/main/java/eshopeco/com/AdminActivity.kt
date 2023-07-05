package eshopeco.com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import eshopeco.com.adapter.AddProductImageAdapter
import eshopeco.com.databinding.ActivityAddProductBinding
import eshopeco.com.databinding.ActivityAdminBinding

class AdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            super.onBackPressed()
        }

//        getOrderDetails()

        binding.addProductBtn.setOnClickListener {
            startActivity(Intent(this, AddProductActivity::class.java))
        }

    }

//    private fun getOrderDetails() {
//        val list = ArrayList<AddProductImageAdapter>()
//        Firebase.firestore.collection("products").whereEqualTo("pro")
//            .get().addOnSuccessListener {
//                list.clear()
//                for (doc in it.documents){
//                    val data = doc.toObject(AddProductImageAdapter::class.java)
//                    list.add(data!!)
//                }
//                val recyclerView = findViewById<RecyclerView>(R.id.adminRecyclerView)
//               recyclerView.adapter = AddProductImageAdapter(this, list)
//            }
//    }
}
/*private fun getCategories() {
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
    }*/