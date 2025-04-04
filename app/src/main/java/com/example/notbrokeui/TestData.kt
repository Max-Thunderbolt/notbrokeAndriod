package com.example.budgetapp

import android.util.Log
import com.example.notbrokeui.Transaction
import java.util.*

/**
 * Utility class to generate test data for the app
 */
object TestData {
    private const val TAG = "TestData"
    
    /**
     * Creates and returns a list of sample transactions
     */
    fun getSampleTransactions(): List<Transaction> {
        Log.d(TAG, "Generating sample transactions")
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
        
        Log.d(TAG, "Generated ${transactions.size} transactions")
        return transactions
    }
} 