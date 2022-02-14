package com.ezatpanah.firebaseforandroidapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ezatpanah.firebaseforandroidapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    val SHARED_PREF = "shared_pref"



    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()



        val userId = intent.getStringExtra("user_id")
        val emailId = intent.getStringExtra("email_id")



        binding.apply {

            tvUserid.text = "User ID : $userId"
            tvEmail.text = "Email ID : $emailId"


            btnLogout.setOnClickListener {
                FirebaseAuth.getInstance().signOut()
                Toast.makeText(this@MainActivity, "Logged Out!", Toast.LENGTH_LONG).show()
                editor.clear()
                editor.apply()
                tvUserid.text = ""
                tvEmail.text = ""
                startActivity(Intent(this@MainActivity, StartActivity::class.java))
            }

        }
    }
}