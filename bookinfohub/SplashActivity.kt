package com.example.bookinfohub

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.logging.Handler

class SplashActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sharedPreferences=getSharedPreferences(getString(R.string.preference_log_in),Context.MODE_PRIVATE)

        val isLogIn=sharedPreferences.getBoolean("isLogIn",false)

        if(isLogIn)
        {
            android.os.Handler().postDelayed({val intent= Intent(this@SplashActivity,MainActivity::class.java)
                startActivity(intent)
                finish()},2000)

        }
        else{
           android.os.Handler().postDelayed({startActivity(Intent(this@SplashActivity,LogInActivity::class.java))
               finish() },2000)
        }
    }
}