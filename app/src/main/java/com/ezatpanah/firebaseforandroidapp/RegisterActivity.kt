package com.ezatpanah.firebaseforandroidapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ezatpanah.firebaseforandroidapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()

        binding.apply {



            btnRegister.setOnClickListener {
                val txtEmail = edtEmailReg.text.toString()
                val txtPassword = edtPasswordReg.text.toString()
                if (txtEmail.isEmpty() || txtPassword.isEmpty())
                {
                    Toast.makeText(this@RegisterActivity, "Empty!", Toast.LENGTH_SHORT).show()
                }
                else if (txtPassword.length< 6){
                    Toast.makeText(this@RegisterActivity, "Password is to short", Toast.LENGTH_SHORT).show()
                }else{
                    registerUser(txtEmail,txtPassword)
                }
            }


        }
    }

    private fun registerUser(txtEmail: String, txtPassword: String) {

        mAuth?.createUserWithEmailAndPassword(txtEmail,txtPassword)?.addOnCompleteListener {
            if (it.isSuccessful){
                val firebaseUser : FirebaseUser = it.result!!.user!!
                Toast.makeText(this@RegisterActivity, "Registering User Successful ", Toast.LENGTH_SHORT).show()
                val intent =Intent(this, MainActivity::class.java)
                intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.putExtra("user_id",firebaseUser.uid)
                intent.putExtra("email_id",txtEmail)
                startActivity(intent)
                finish()

            }else{
                Toast.makeText(this@RegisterActivity, it.exception!!.message, Toast.LENGTH_SHORT).show()

            }
        }
    }
}