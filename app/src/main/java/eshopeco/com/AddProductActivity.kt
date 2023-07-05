package eshopeco.com

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.VISIBLE
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import eshopeco.com.adapter.AddProductImageAdapter
import eshopeco.com.databinding.ActivityAddProductBinding
import eshopeco.com.model.AddProductModel
import eshopeco.com.model.CategoryModel
import java.util.*
import kotlin.collections.ArrayList

class AddProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProductBinding
    private lateinit var list: ArrayList<Uri>
    private lateinit var listImage: ArrayList<String>
    private lateinit var adapter: AddProductImageAdapter
    private var coverImage: Uri? = null
    private lateinit var dialog: Dialog
    private var coverImgUrl: String? = ""
    private lateinit var categoryList: ArrayList<String>

    private var launchGalleryActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if (it.resultCode == Activity.RESULT_OK){
            coverImage = it.data!!.data
            binding.productCoverImg.setImageURI(coverImage)
            binding.productCoverImg.visibility = VISIBLE
        }
    }

    private var launchProductActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if (it.resultCode == Activity.RESULT_OK){
            val imageUrl = it.data!!.data
            list.add(imageUrl!!)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            super.onBackPressed()
        }

        list = ArrayList()
        listImage = ArrayList()

        dialog = Dialog(this)
        dialog.setContentView(R.layout.progress_layout)
        dialog.setCancelable(false)

        binding.selectCover.setOnClickListener{
            val intent = Intent("android.intent.action.GET_CONTENT")
            intent.type = "image/*"
            launchGalleryActivity.launch(intent)
        }

        binding.productImageBtn.setOnClickListener{
             val intent = Intent("android.intent.action.GET_CONTENT")
            intent.type = "image/*"
            launchProductActivity.launch(intent)
        }

        setProductCategory()

        adapter = AddProductImageAdapter(list)
        binding.productRecyclerView.adapter = adapter

        binding.submitBtn.setOnClickListener {
            validateData()
        }

    }

    private fun validateData() {
        if (binding.productNameTxt.text.toString().isEmpty()){
            binding.productNameTxt.requestFocus()
            binding.productNameTxt.error = "Empty"
        }else if(binding.productSpTxt.text.toString().isEmpty()){
            binding.productSpTxt.requestFocus()
            binding.productSpTxt.error = "Empty"
        }else if(coverImage == null) {
            Toast.makeText(this, "Please select cover image", Toast.LENGTH_SHORT).show()
        }else if(list.size < 1) {
            Toast.makeText(this, "Please select product image", Toast.LENGTH_SHORT).show()
        }else{
            uploadImage()
        }
    }

    private fun uploadImage() {
        dialog.show()

        val fileName = UUID.randomUUID().toString()+".jpg"

        val refStorage = FirebaseStorage.getInstance().reference.child("products/$fileName")
        refStorage.putFile(coverImage!!)
            .addOnSuccessListener {
                it.storage.downloadUrl.addOnSuccessListener { image ->
                    coverImgUrl = image.toString()

                    uploadProductImage()
                }
            }.addOnFailureListener{
                dialog.dismiss()
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
    }

    private var i = 0
    private fun uploadProductImage() {
        dialog.show()

        val fileName = UUID.randomUUID().toString()+".jpg"

        val refStorage = FirebaseStorage.getInstance().reference.child("products/$fileName")
        refStorage.putFile(list[i])
            .addOnSuccessListener {
                it.storage.downloadUrl.addOnSuccessListener { image ->
                    listImage.add(image!!.toString())

                    if (list.size == listImage.size){
                    storeData()
                }else {
                        i +=1
                        uploadProductImage()
                    }
                    }
            }.addOnFailureListener{
                dialog.dismiss()
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
    }

    private fun storeData() {
        val db = Firebase.firestore.collection("products")
        val key = db.document().id

        val data = AddProductModel(
            binding.productNameTxt.text.toString(),
            binding.productDescTxt.text.toString(),
            coverImgUrl.toString(),
            categoryList[binding.productCateDrop.selectedItemPosition],
            key,
            binding.productPriceTxt.text.toString(),
            binding.productSpTxt.text.toString(),
            listImage
        )

        db.document(key).set(data).addOnSuccessListener {
            dialog.dismiss()
            Toast.makeText(this, "Product added", Toast.LENGTH_SHORT).show()
            binding.productNameTxt.text = null
        } .addOnFailureListener {
            dialog.dismiss()
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setProductCategory(){
        categoryList = ArrayList()
        Firebase.firestore.collection("categories").get().addOnSuccessListener {
            categoryList.clear()
            for (doc in it.documents){
                val data = doc.toObject(CategoryModel::class.java)
                categoryList.add(data!!.cate!!)
            }
            categoryList.add(0,"Select Category")

            val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, categoryList)
            binding.productCateDrop.adapter = arrayAdapter
        }
    }
}


