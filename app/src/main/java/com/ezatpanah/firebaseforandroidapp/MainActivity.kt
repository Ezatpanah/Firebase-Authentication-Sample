package com.ezatpanah.firebaseforandroidapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ezatpanah.firebaseforandroidapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userId = intent.getStringExtra("user_id")
        val emailId = intent.getStringExtra("email_id")
        binding.apply {

            tvUserid.text="User ID : $userId"
            tvEmail.text="Email ID : $emailId"


            btnLogout.setOnClickListener {
                FirebaseAuth.getInstance().signOut()
                Toast.makeText(this@MainActivity, "Logged Out!", Toast.LENGTH_LONG).show()
                startActivity(Intent(this@MainActivity,StartActivity::class.java))
            }

        }
    }
}