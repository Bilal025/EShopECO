package eshopeco.com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import eshopeco.com.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.loginBtn.setOnClickListener {
            val emailTxt = binding.logEmailEt.text.toString().trim()
            val passwordTxt = binding.logPwEt.text.toString().trim()
            loginUser(emailTxt, passwordTxt)
        }
        binding.haventAccount.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }

    private fun loginUser(emailTxt: String, passwordTxt: String) {
        if (!TextUtils.isEmpty(emailTxt) && Patterns.EMAIL_ADDRESS.matcher(emailTxt).matches() &&
            !TextUtils.isEmpty(passwordTxt) && passwordTxt.length > 7
        ) {

            auth.signInWithEmailAndPassword(emailTxt, passwordTxt)
                .addOnCompleteListener(this) { login ->
                    if (login.isSuccessful) {
                        Intent(this, HomeActivity::class.java).also {
                            it.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(it)
                            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "User name or password wrong", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        } else {
            Toast.makeText(this, "Please enter a valid email and password", Toast.LENGTH_SHORT)
                .show()
        }
    }
}