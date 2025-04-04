package com.example.notbrokeui

data class Transaction(
    var id: String = "", // Default empty string for Firebase
    var name: String = "",
    var amount: Double = 0.0,
    var date: Long = 0L,
    var type: Type = Type.EXPENSE,
    var category: String = ""
) {
    enum class Type {
        INCOME, EXPENSE
    }
    
    // Empty constructor required for Firebase
    constructor() : this("", "", 0.0, 0L, Type.EXPENSE, "")
} 