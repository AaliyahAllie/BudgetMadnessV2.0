package com.example.budgetmadnessv20

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
//navigates to user navigation screen / menu screen

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        //redirects user to home screen on click
        findViewById<Button>(R.id.btnHome).setOnClickListener {
            logEvent("User clicked on Home button")
            startActivity(Intent(this, HomeScreenActivity::class.java))
        }
        //redirects user to profile on click
        findViewById<Button>(R.id.btnProfile).setOnClickListener {
            logEvent("User clicked on Profile button")
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        //redirects to expenses on click
        findViewById<Button>(R.id.btnViewExpenses).setOnClickListener {
            logEvent("User clicked on View Expenses button")
            startActivity(Intent(this, ExpenseViewActivity::class.java))
        }
        //allows for adding of expenses on click
        findViewById<Button>(R.id.btnAddExpense).setOnClickListener {
            logEvent("User clicked on Add Expense button")
            startActivity(Intent(this, AddExpensesActivity::class.java))
        }
        //redirects to income screen on click
        findViewById<Button>(R.id.btnIncome).setOnClickListener {
            logEvent("User clicked on Income button")
            startActivity(Intent(this, IncomeActivity::class.java))
        }
        //displays user balance and history on click
        findViewById<Button>(R.id.btnBalance).setOnClickListener {
            logEvent("User clicked on Balance button")
            startActivity(Intent(this, BalanceActivity::class.java))
        }
        //redirects user budget
        findViewById<Button>(R.id.btnBudget).setOnClickListener {
            logEvent("User clicked on Budget button")
            startActivity(Intent(this, BudgetActivity::class.java))
        }
        //redirects to categories
        findViewById<Button>(R.id.btnCategories).setOnClickListener {
            logEvent("User clicked on Categories button")
            startActivity(Intent(this, CategoriesActivity::class.java))
        }
        findViewById<Button>(R.id.btnBudgetView).setOnClickListener {
            logEvent("User clicked on BudgetView button")
            startActivity(Intent(this, BudgetViewActivity::class.java))
        }
    }

    private fun logEvent(message: String) {
        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val logMessage = "$timestamp - $message"
        android.util.Log.d("MenuActivityLog", logMessage)

        try {
            val file = File(getExternalFilesDir(null), "app_log.txt")
            val writer = FileWriter(file, true)
            writer.appendLine(logMessage)
            writer.close()
        } catch (e: IOException) {
            android.util.Log.e("MenuActivityLog", "Failed to write log: ${e.message}")
        }
    }
}