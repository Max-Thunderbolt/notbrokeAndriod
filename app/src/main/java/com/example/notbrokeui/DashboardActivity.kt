package com.example.notbrokeui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class DashboardActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var transactionsRecyclerView: RecyclerView
    private lateinit var transactionAdapter: TransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            Log.d("DashboardActivity", "Starting onCreate")
            
            // Set content view without background first to avoid resource loading issues
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_dashboard)
            
            // Check and handle background resources safely
            try {
                val coordinatorLayout = findViewById<androidx.coordinatorlayout.widget.CoordinatorLayout>(R.id.dashboard_activity)
                if (coordinatorLayout != null) {
                    Log.d("DashboardActivity", "Found coordinator layout, checking background")
                    // Try setting a solid color background instead of an image to eliminate potential resource issues
                    coordinatorLayout.setBackgroundColor(android.graphics.Color.parseColor("#1E1E1E"))
                } else {
                    Log.e("DashboardActivity", "Could not find coordinator layout!")
                }
            } catch (e: Exception) {
                Log.e("DashboardActivity", "Error handling background", e)
            }

            try {
                // Initialize UI components
                bottomNavigationView = findViewById(R.id.bottom_navigation)
                setupBottomNavigation()
            } catch (e: Exception) {
                Log.e("DashboardActivity", "Error setting up navigation", e)
                showToast("Navigation error: ${e.message}")
            }
            
            try {
                // Default to transactions tab (current screen)
                bottomNavigationView.selectedItemId = R.id.nav_transactions
            } catch (e: Exception) {
                Log.e("DashboardActivity", "Error selecting initial tab", e)
            }
            
            try {
                // Setup RecyclerView for transactions
                setupTransactionsRecyclerView()
                
                // Load sample transactions
                loadSampleTransactions()
            } catch (e: Exception) {
                Log.e("DashboardActivity", "Error setting up transactions", e)
                showToast("Transactions error: ${e.message}")
            }
            
            Log.d("DashboardActivity", "onCreate completed")
        } catch (e: Exception) {
            Log.e("DashboardActivity", "Critical error in onCreate", e)
            try {
                showToast("Critical error: ${e.message}")
            } catch (t: Throwable) {
                // Nothing more we can do
            }
        }
    }
    
    private fun setupTransactionsRecyclerView() {
        try {
            Log.d("DashboardActivity", "Setting up transactions RecyclerView")
            
            // Find the RecyclerView in the layout
            transactionsRecyclerView = findViewById(R.id.transactionsRecyclerView)
            if (transactionsRecyclerView == null) {
                Log.e("DashboardActivity", "Failed to find transactionsRecyclerView!")
                return
            }
            
            // Create and set the adapter
            transactionAdapter = TransactionAdapter()
            
            // Configure the RecyclerView
            transactionsRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@DashboardActivity)
                adapter = transactionAdapter
            }
            
            Log.d("DashboardActivity", "Transactions RecyclerView setup complete")
        } catch (e: Exception) {
            Log.e("DashboardActivity", "Error setting up transactions RecyclerView", e)
            showToast("Error setting up transactions: ${e.message}")
        }
    }

    private fun loadSampleTransactions() {
        try {
            Log.d("DashboardActivity", "Starting to load sample transactions")
            val calendar = Calendar.getInstance()
            
            val transactions = mutableListOf<Transaction>()
            
            // Today's transaction
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            transactions.add(
                Transaction(
                    id = "1",
                    name = "Groceries at Woolworths",
                    amount = 450.75,
                    date = calendar.timeInMillis,
                    type = Transaction.Type.EXPENSE,
                    category = "Groceries"
                )
            )
            
            // Yesterday's transactions
            calendar.add(Calendar.DAY_OF_MONTH, -1)
            transactions.add(
                Transaction(
                    id = "2",
                    name = "Salary Payment",
                    amount = 15000.00,
                    date = calendar.timeInMillis,
                    type = Transaction.Type.INCOME,
                    category = "Salary"
                )
            )
            
            calendar.add(Calendar.HOUR_OF_DAY, 5)
            transactions.add(
                Transaction(
                    id = "3",
                    name = "Dinner at Steers",
                    amount = 180.50,
                    date = calendar.timeInMillis,
                    type = Transaction.Type.EXPENSE,
                    category = "Food"
                )
            )
            
            // Last week's transactions
            calendar.add(Calendar.DAY_OF_MONTH, -5)
            transactions.add(
                Transaction(
                    id = "4",
                    name = "Netflix Subscription",
                    amount = 159.00,
                    date = calendar.timeInMillis,
                    type = Transaction.Type.EXPENSE,
                    category = "Entertainment"
                )
            )
            
            calendar.add(Calendar.DAY_OF_MONTH, -2)
            transactions.add(
                Transaction(
                    id = "5",
                    name = "Freelance Project",
                    amount = 3500.00,
                    date = calendar.timeInMillis,
                    type = Transaction.Type.INCOME,
                    category = "Freelance"
                )
            )
            
            transactions.add(
                Transaction(
                    id = "6",
                    name = "Fuel",
                    amount = 650.00,
                    date = calendar.timeInMillis,
                    type = Transaction.Type.EXPENSE,
                    category = "Transport"
                )
            )
            
            // Check if we have transactions
            if (transactions.isEmpty()) {
                Log.w("DashboardActivity", "Warning: No sample transactions were created")
            } else {
                Log.d("DashboardActivity", "Created ${transactions.size} sample transactions")
            }
            
            // Display transactions - check for null adapter
            if (::transactionAdapter.isInitialized) {
                transactionAdapter.setTransactions(transactions)
                Log.d("DashboardActivity", "Set transactions on adapter")
            } else {
                Log.e("DashboardActivity", "TransactionAdapter not initialized!")
            }
            
            // Update balance
            updateBalance(transactions)
            
            Log.d("DashboardActivity", "Sample transactions loaded successfully")
        } catch (e: Exception) {
            Log.e("DashboardActivity", "Error loading sample transactions", e)
            showToast("Could not load transaction data: ${e.message}")
        }
    }
    
    private fun updateBalance(transactions: List<Transaction>) {
        var balance = 0.0
        for (transaction in transactions) {
            if (transaction.type == Transaction.Type.INCOME) {
                balance += transaction.amount
            } else {
                balance -= transaction.amount
            }
        }
        
        val balanceTextView = findViewById<android.widget.TextView>(R.id.balanceTextView)
        balanceTextView.text = "R%.2f".format(balance)
    }

    private fun setupBottomNavigation() {
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_transactions -> {
                    // Show transactions screen (main dashboard)
                    showMainDashboard()
                    true
                }
                R.id.nav_budget -> {
                    try {
                        // Instead of a fragment, use a Toast for the budget feature
                        Log.d("DashboardActivity", "Displaying budget toast message")
                        showToast("Budget feature coming soon")
                        
                        // Keep showing the transactions view
                        showMainDashboard()
                        true
                    } catch (e: Exception) {
                        Log.e("DashboardActivity", "Error showing budget message", e)
                        showToast("Error: ${e.message}")
                        true
                    }
                }
                R.id.nav_progression -> {
                    // Will navigate to Progression screen
                    showToast("Progression feature coming soon")
                    true
                }
                R.id.nav_debt -> {
                    // Will navigate to Debt screen
                    showToast("Debt feature coming soon")
                    true
                }
                R.id.nav_habits -> {
                    // Will navigate to Habits screen
                    showToast("Habits feature coming soon")
                    true
                }
                else -> false
            }
        }
    }
    
    private fun showMainDashboard() {
        try {
            // Simply make sure the dashboard content is visible
            findViewById<androidx.core.widget.NestedScrollView>(R.id.dashboardScrollView).visibility = android.view.View.VISIBLE
            
            // And make sure the fragment container is gone (hidden)
            findViewById<android.widget.FrameLayout>(R.id.fragmentContainer).visibility = android.view.View.GONE
            
            Log.d("DashboardActivity", "Showing main dashboard")
        } catch (e: Exception) {
            Log.e("DashboardActivity", "Error showing main dashboard", e)
            // Just show a toast if there's an error
            showToast("Error showing main view: ${e.message}")
        }
    }
    
    private fun loadFragment(fragment: Fragment) {
        try {
            // Hide the main dashboard content first
            val dashboardContent = findViewById<androidx.core.widget.NestedScrollView>(R.id.dashboardScrollView)
            dashboardContent.visibility = android.view.View.GONE
            
            // Make fragment container visible
            val fragmentContainer = findViewById<android.widget.FrameLayout>(R.id.fragmentContainer)
            fragmentContainer.visibility = android.view.View.VISIBLE
            
            // Use a handler to delay the fragment transaction slightly, allowing the UI to update first
            android.os.Handler(mainLooper).postDelayed({
                try {
                    // Load the fragment
                    Log.d("DashboardActivity", "Starting fragment transaction")
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        .commitAllowingStateLoss() // Using commitAllowingStateLoss for more stability
                    Log.d("DashboardActivity", "Fragment transaction committed")
                } catch (e: Exception) {
                    Log.e("DashboardActivity", "Error in delayed fragment transaction", e)
                    showToast("Error loading screen: ${e.message}")
                    // Revert to main dashboard
                    showMainDashboard()
                }
            }, 100) // Slight delay of 100ms
        } catch (e: Exception) {
            Log.e("DashboardActivity", "Error in loadFragment", e)
            showToast("Error loading screen: ${e.message}")
            // Revert to main dashboard
            showMainDashboard()
        }
    }

    private fun showToast(message: String) {
        android.widget.Toast.makeText(this, message, android.widget.Toast.LENGTH_SHORT).show()
    }
} 