package com.example.notbrokeui

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

/**
 * Ultra-simplified BudgetFragment to isolate the crash issue
 */
class BudgetFragment : Fragment() {
    
    private val TAG = "BudgetFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView: Creating simplified BudgetFragment")
        
        // Create a simple container
        val rootLayout = LinearLayout(requireContext()).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
            
            try {
                // Set a neutral background color that won't conflict with anything
                setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.darker_gray))
            } catch (e: Exception) {
                Log.e(TAG, "Error setting background color", e)
                setBackgroundColor(0xFF666666.toInt())
            }
        }
        
        // Add a simple TextView with a message
        val messageView = TextView(requireContext()).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            
            text = "Budget Feature\nComing Soon"
            textSize = 24f
            gravity = Gravity.CENTER
            
            try {
                setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
            } catch (e: Exception) {
                Log.e(TAG, "Error setting text color", e)
                setTextColor(0xFFFFFFFF.toInt())
            }
        }
        
        // Add the TextView to the container
        rootLayout.addView(messageView)
        
        Log.d(TAG, "onCreateView: Simplified BudgetFragment created successfully")
        return rootLayout
    }
    
    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView: Cleaning up simplified BudgetFragment")
        super.onDestroyView()
    }
    
    // Simple BudgetCategory class to maintain compatibility with adapter
    data class BudgetCategory(
        val name: String,
        var budgetAmount: Double,
        var spentAmount: Double
    ) {
        val percentUsed: Double
            get() = if (budgetAmount > 0) (spentAmount / budgetAmount) * 100 else 0.0
    }
} 