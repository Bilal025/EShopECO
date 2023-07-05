package eshopeco.com.fragment

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import eshopeco.com.R
import eshopeco.com.adapter.CartAdapter
import eshopeco.com.databinding.FragmentCartBinding
import eshopeco.com.model.CartModel



class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private lateinit var cartList: ArrayList<CartModel>
    private lateinit var auth: FirebaseAuth
    private lateinit var adapter: CartAdapter
    private var totalPrice = 0

    private var orderDatabaseReference = Firebase.firestore.collection("orders")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCartBinding.bind(view)
        auth = FirebaseAuth.getInstance()

        val layoutManager = LinearLayoutManager(context)

        cartList = ArrayList()

        retrieveItemsCart()

        adapter = CartAdapter(requireContext(),cartList)
        binding.cartItemRecyclerView.adapter = adapter
        binding.cartItemRecyclerView.layoutManager = layoutManager
    }

    private fun retrieveItemsCart() {
        orderDatabaseReference.whereEqualTo("uid",auth.currentUser!!.uid)
            .get().addOnSuccessListener {querySnapshot ->
                for (item in querySnapshot){
                    val cartProduct = item.toObject<CartModel>()

                    cartList.add(cartProduct)
                    totalPrice += cartProduct.productPrice!!.toInt()

                    binding.LastTotalPrice.text = totalPrice.toString()
                    adapter.notifyDataSetChanged()
                }

            }.addOnFailureListener {
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
            }
    }

}
