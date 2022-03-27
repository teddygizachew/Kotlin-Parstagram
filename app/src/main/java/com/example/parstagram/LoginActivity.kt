package com.example.parstagram

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {

    lateinit var btnLogin: Button
    lateinit var tvSignUp: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // check if the user is logged in
        if (ParseUser.getCurrentUser() != null) {
            // do stuff with the user
            goToMainActivity()
        }

        tvSignUp = findViewById(R.id.tvSignup)

        btnLogin = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener {
            val username = findViewById<EditText>(R.id.etUsername).text.toString()
            val password = findViewById<EditText>(R.id.etPassword).text.toString()

            loginUser(username, password)
        }

        tvSignUp.setOnClickListener {
            goToSignupActivity()
        }
    }

    private fun loginUser(username: String, password: String) {
        Log.i(TAG, "Attempting to login user: $username")
        // Network call on the background thread instead of the main thread
        ParseUser.logInInBackground(
            username, password, ({ user, e ->
                if (user != null) {
                    // Hooray!  The user is logged in.
                    Log.i(TAG, "Successfully logged in user")
                    goToMainActivity()
                } else {
                    // Signup failed.  Look at the ParseException to see what happened.
                    e.printStackTrace()
                    Log.e(TAG, "Issue with login", e)
                    Toast.makeText(this, "Error logging in", Toast.LENGTH_SHORT).show()
                }
            })
        )
    }

    private fun goToMainActivity() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToSignupActivity() {
        val intent = Intent(this@LoginActivity, SignupActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        const val TAG = "LoginActivity"
    }

}