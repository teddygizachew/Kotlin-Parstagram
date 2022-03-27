package com.example.parstagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.parse.ParseUser

class SignupActivity : AppCompatActivity() {

    lateinit var btnSignup: Button
    lateinit var tvSignIn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        btnSignup = findViewById(R.id.btnSignup)
        btnSignup.setOnClickListener {
            val username = findViewById<EditText>(R.id.etSignupUsername).text.toString()
            val password = findViewById<EditText>(R.id.etSignupPassword).text.toString()

            signUpUser(username, password)
        }

        tvSignIn = findViewById(R.id.tvSignIn)
        tvSignIn.setOnClickListener {
            goToLoginActivity()
        }
    }

    private fun signUpUser(username: String, password: String) {
        // Create the ParseUser
        val user = ParseUser()
        // Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {
                // User has now successfully created a new account
                goToMainActivity()
                Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()
            } else {
                // Sign up didn't succeed. Look at the ParseException
                // to figure out what went wrong
                Toast.makeText(this, "Signup not successful", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun goToMainActivity() {
        val intent = Intent(this@SignupActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToLoginActivity() {
        val intent = Intent(this@SignupActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}