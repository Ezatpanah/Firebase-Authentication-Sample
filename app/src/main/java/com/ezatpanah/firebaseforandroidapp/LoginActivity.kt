package com.ezatpanah.firebaseforandroidapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ezatpanah.firebaseforandroidapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var mAuth: FirebaseAuth? = null

    val SHARED_PREF ="shared_pref"




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)




        mAuth = FirebaseAuth.getInstance();

        binding.apply {

            btnLogin.setOnClickListener {
                val txtEmail = edtEmailLogin.text.toString()
                val txtPassword = edtPasswordLogin.text.toString()

                if (txtEmail.isEmpty() || txtPassword.isEmpty())
                {
                    Toast.makeText(this@LoginActivity, "Empty!", Toast.LENGTH_SHORT).show()
                }
                else{
                    loginUser(txtEmail,txtPassword)                }
            }

            tvRegisterUser.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
                finish()
            }

        }
    }

    private fun loginUser(txtEmail: String, txtPassword: String) {

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor =  sharedPreferences.edit()

        mAuth!!.signInWithEmailAndPassword(txtEmail, txtPassword)
            .addOnCompleteListener(this) { task ->
                Log.d("TAG", "signInWithEmail:onComplete:" + task.isSuccessful)
                if (task.isSuccessful) {
                    val firebaseUser : FirebaseUser = task.result!!.user!!
                    Toast.makeText(this, "Login Successful ", Toast.LENGTH_SHORT).show()
                    val intent =Intent(this, MainActivity::class.java)
                    intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    intent.putExtra("user_id",firebaseUser.uid)
                    intent.putExtra("email_id",txtEmail)

                    editor.putString("user_id",firebaseUser.uid)
                    editor.putString("email_id",txtEmail)
                    editor.apply()
                    editor.commit()

                    startActivity(intent)
                    finish()

                }else{
                    Log.w("TAG", "signInWithEmail", task.exception)
                    Toast.makeText(this@LoginActivity, "Authentication failed.",Toast.LENGTH_SHORT).show()
                }
            }
    }

}