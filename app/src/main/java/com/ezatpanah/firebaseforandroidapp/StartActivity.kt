package com.ezatpanah.firebaseforandroidapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ezatpanah.firebaseforandroidapp.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    private lateinit var binding :ActivityStartBinding

    val SHARED_PREF = "shared_pref"





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()


        val sharedUserId = sharedPreferences.getString("user_id","")
        val sharedEmailId= sharedPreferences.getString("email_id","")

        Toast.makeText(this, "$sharedUserId  $sharedEmailId ", Toast.LENGTH_SHORT).show()


            binding.apply {

                if(sharedUserId.equals("") && sharedEmailId.equals("")){
                    btnLogin.isEnabled=true
                    btnReg.isEnabled=true
                }else{
                    val intent =Intent(this@StartActivity, MainActivity::class.java)
                    intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    intent.putExtra("user_id",sharedUserId)
                    intent.putExtra("email_id",sharedEmailId)
                    startActivity(intent)
                    finish()
                }

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