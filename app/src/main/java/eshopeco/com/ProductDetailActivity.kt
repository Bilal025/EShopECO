package eshopeco.com

import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ImageView.ScaleType
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.slider.Slider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import eshopeco.com.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getProductDetail(intent.getStringExtra("id"))
    }

    private fun getProductDetail(productId: String?) {

        Firebase.firestore.collection("productId").document(productId!!).get().addOnSuccessListener {

            val proName = it.getString("productName")
            val proPrice = it.getString("productPrice")
            val proDesc = it.getString("productDescription")

            binding.proTitle.text = proName
            binding.proPrice.text = proPrice
            binding.proDesc.text = proDesc

            Glide.with(this).load(it.get("productImages")).into(binding.Slideview)


        }.addOnFailureListener {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
        }

    }


}