package com.example.esoftwarica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var btnlogin:Button
    private lateinit var etusername:EditText
    private lateinit var etpassword:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Binding

        etusername=findViewById(R.id.etusername)
        etpassword=findViewById(R.id.etpassword)
        btnlogin=findViewById(R.id.btnlogin)

        btnlogin.setOnClickListener {

            if (!validated()) return@setOnClickListener

            if (!loginattempt()){
                Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show()
                etusername.setError("Incorrect Username or Password")
                return@setOnClickListener

            }

            val dashboardActivity=Intent(this,dashboard_activity::class.java)
            startActivity(dashboardActivity)
            finish()
        }
    }

    private fun loginattempt(): Boolean {

        if (etusername.text.toString()=="softwarica" && etpassword.text.toString()=="coventry")
        {
            return true
        }
        return false

    }

    private fun validated():Boolean {

        var validation = true

        if(etusername.text.isEmpty())
        {
            etusername.setError("username cannot be empty")
            validation=false
        }

        if (etpassword.text.isEmpty())
        {
            etpassword.setError("password cannot be empty")
            validation=false
        }
        return validation
    }
}