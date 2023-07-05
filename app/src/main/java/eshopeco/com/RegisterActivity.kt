package eshopeco.com

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import eshopeco.com.databinding.ActivityLoginBinding
import eshopeco.com.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Firebase auth
        auth = FirebaseAuth.getInstance()

        binding.registerBtn.setOnClickListener{
            val emailTxt = binding.registerEmailEt.text.toString().trim()
            val passwordTxt = binding.registerPwEt.text.toString().trim()
            registerUser(emailTxt, passwordTxt)
        }
        binding.haveAccount.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun registerUser(emailTxt: String, passwordTxt:String){
        if(!TextUtils.isEmpty(emailTxt) && Patterns.EMAIL_ADDRESS.matcher(emailTxt).matches() &&
            !TextUtils.isEmpty(passwordTxt) && passwordTxt.length > 7) {

            auth.createUserWithEmailAndPassword(emailTxt, passwordTxt)
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {

                        Toast.makeText(this, "User added successful, Please login to continue", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, LoginActivity::class.java))

                    } else {

                        Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show()
                    }
                }
        }else {
            Toast.makeText(this, "Please enter a valid email and password", Toast.LENGTH_SHORT)
                .show()
        }

    }
}