package com.example.notbrokeui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        
        Log.d(TAG, "onCreate: Starting login activity")
        
        // Initialize UI components
        setupUI()
    }
    
    private fun setupUI() {
        // Find views
        val loginButton = findViewById<MaterialButton>(R.id.loginButton)
        val registerButton = findViewById<MaterialButton>(R.id.registerButton)
        val emailEditText = findViewById<TextInputEditText>(R.id.emailEditText)
        val passwordEditText = findViewById<TextInputEditText>(R.id.passwordEditText)
        
        // Set login button click listener
        loginButton.setOnClickListener {
            Log.d(TAG, "Login button clicked")
            // For now, just navigate to dashboard without authentication
            navigateToDashboard()
        }
        
        // Set register button click listener
        registerButton.setOnClickListener {
            Log.d(TAG, "Register button clicked")
            navigateToRegistration()
        }
    }
    
    private fun navigateToDashboard() {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
    }
    
    private fun navigateToRegistration() {
        // Create RegisterActivity.kt and activity_register.xml later
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}