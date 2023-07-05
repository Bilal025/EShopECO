package eshopeco.com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import eshopeco.com.databinding.ActivityHomeBinding
import eshopeco.com.fragment.AdminFragment
import eshopeco.com.fragment.CartFragment
import eshopeco.com.fragment.CategoryFragment
import eshopeco.com.fragment.HomeFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val auth = FirebaseAuth.getInstance()

        binding.bottomNavigationBar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.homeFragment -> replaceFragment(HomeFragment())
                R.id.categoryFragment -> replaceFragment(CategoryFragment())
                R.id.cartFragment -> replaceFragment(CartFragment())
                R.id.adminFragment -> replaceFragment(AdminFragment())
                R.id.logoutNav -> {
                    auth.signOut()
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                else -> {
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmenManager = supportFragmentManager
        val fragmentTransaction = fragmenManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
    }
}