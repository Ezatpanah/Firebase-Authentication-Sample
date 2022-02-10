package com.ezatpanah.firebaseforandroidapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ezatpanah.firebaseforandroidapp.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    private lateinit var binding :ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnLogin.setOnClickListener {
                startActivity(Intent(this@StartActivity, LoginActivity::class.java))
                finish()
            }

            btnReg.setOnClickListener {
                startActivity(Intent(this@StartActivity, RegisterActivity::class.java))
                finish()
            }
        }
    }
}