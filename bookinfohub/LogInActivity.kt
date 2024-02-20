package com.example.bookinfohub

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LogInActivity : AppCompatActivity() {
    lateinit var editTextMobileNumber: EditText
    lateinit var editTextPassword:EditText
    lateinit var btnLogIn:Button
    lateinit var txtvwSignUp:TextView
    lateinit var sharedPreference:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        editTextMobileNumber=findViewById(R.id.edttxtLogInMobileNumber)
        editTextPassword=findViewById(R.id.edttxtLogInPassWord)
        btnLogIn=findViewById(R.id.btnLogInLog)
        txtvwSignUp=findViewById(R.id.txtvwLogInSignUp)

        sharedPreference=getSharedPreferences(getString(R.string.preference_log_in), Context.MODE_PRIVATE)

        txtvwSignUp.setOnClickListener{
            startActivity(Intent(this@LogInActivity,RegisterActivity::class.java))
        }

        btnLogIn.setOnClickListener{
            val MobileNumber=editTextMobileNumber.text.toString()
            val password=editTextPassword.text.toString()
            if(MobileNumber=="7075582592" && password=="sainath"){
                sharedPreference.edit().putBoolean("isLogIn",true).apply()
                startActivity(Intent(this@LogInActivity,MainActivity::class.java))
                finish()
            }
            else{
                Toast.makeText(this@LogInActivity,"Invalid credentials!",Toast.LENGTH_SHORT).show()
            }
        }


    }
}