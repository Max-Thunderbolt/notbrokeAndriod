package com.example.notbrokeui

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import com.google.android.material.card.MaterialCardView

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    private var transactions: List<Transaction> = ArrayList()
    private val dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
    private val TAG = "TransactionAdapter"

    fun setTransactions(transactions: List<Transaction>) {
        this.transactions = transactions
        Log.d(TAG, "setTransactions: Setting ${transactions.size} transactions")
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        Log.d(TAG, "onCreateViewHolder: Creating new view holder")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: Binding data at position $position")
        holder.bind(transactions[position])
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount: Returning ${transactions.size} items")
        return transactions.size
    }

    inner class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.transactionNameTextView)
        private val dateTextView: TextView = itemView.findViewById(R.id.transactionDateTextView)
        private val amountTextView: TextView = itemView.findViewById(R.id.transactionAmountTextView)
        private val cardView: MaterialCardView = itemView as MaterialCardView

        fun bind(transaction: Transaction) {
            nameTextView.text = transaction.name
            dateTextView.text = dateFormat.format(Date(transaction.date))
            
            val amountText: String
            if (transaction.type == Transaction.Type.EXPENSE) {
                amountText = "-R%.2f".format(transaction.amount)
                amountTextView.setTextColor(Color.parseColor("#E91E1E")) // Red for expenses
                cardView.strokeColor = Color.parseColor("#E91E1E") // Red border
                
                // Set slightly darker background for expenses
                cardView.setCardBackgroundColor(Color.parseColor("#252525"))
            } else {
                amountText = "+R%.2f".format(transaction.amount)
                amountTextView.setTextColor(Color.parseColor("#3CE91E")) // Green for income
                cardView.strokeColor = Color.parseColor("#3CE91E") // Green border
                
                // Set slightly lighter background for income
                cardView.setCardBackgroundColor(Color.parseColor("#2A2A2A"))
            }
            amountTextView.text = amountText
            
            // Apply slight elevation variations randomly to create depth effect
            val randomElevation = 2f + (position % 3) * 1f
            cardView.cardElevation = randomElevation
        }
    }
} 