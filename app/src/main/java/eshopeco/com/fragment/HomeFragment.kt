package eshopeco.com.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import eshopeco.com.adapter.ProductAdapter
import eshopeco.com.databinding.FragmentHomeBinding
import eshopeco.com.model.ProductModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {


        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

       getProducts()

        return binding.root

    }

    private fun getProducts() {
        val list = ArrayList<ProductModel>()
        Firebase.firestore.collection("products")
            .get().addOnSuccessListener{
                list.clear()
                for (doc in it.documents) {
                    val data = doc.toObject(ProductModel::class.java)
                    list.add(data!!)
                }
                binding.homeProductRecy.adapter = ProductAdapter(requireContext(),list)
            }
    }
}
