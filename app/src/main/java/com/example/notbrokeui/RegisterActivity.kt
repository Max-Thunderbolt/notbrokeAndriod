package com.example.notbrokeui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class RegisterActivity : AppCompatActivity() {
    private val TAG = "RegisterActivity"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        
        Log.d(TAG, "onCreate: Starting registration activity")
        
        // Initialize UI components
        setupUI()
    }
    
    private fun setupUI() {
        // Find views
        val registerButton = findViewById<MaterialButton>(R.id.registerSubmitButton)
        val loginLinkButton = findViewById<MaterialButton>(R.id.loginLinkButton)
        
        // Set register button click listener
        registerButton.setOnClickListener {
            Log.d(TAG, "Register button clicked")
            // For now, just navigate to dashboard without actual registration
            navigateToDashboard()
        }
        
        // Set login link button click listener
        loginLinkButton.setOnClickListener {
            Log.d(TAG, "Login link clicked")
            // Simply finish this activity to go back to login
            finish()
        }
    }
    
    private fun navigateToDashboard() {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish() // Close the registration activity
    }
} 