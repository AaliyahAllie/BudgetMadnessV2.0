
package com.example.budgetmadnessv20

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
//this screen will display the users total balance and their transaction history
class BalanceActivity : AppCompatActivity() {

    private val TAG = "BalanceActivity"
    //connection to database
    private lateinit var dbHelper: IncomeDatabaseHelper
    private lateinit var expenseDbHelper: BudgetDatabaseHelper
    private lateinit var totalIncomeText: TextView
    private lateinit var recyclerViewIncome: RecyclerView
    private lateinit var recyclerViewExpenses: RecyclerView
    private lateinit var incomeAdapter: IncomeHistoryAdapter
    private lateinit var expenseAdapter: ExpenseHistoryAdapter
    //will rebase itself every 2 seconds to keep an uptodate total balance
    private val handler = Handler(Looper.getMainLooper())
    private val updateInterval = 2000L // 2 seconds
    //updates history of income transactions
    private val incomeUpdater = object : Runnable {
        override fun run() {
            val totalIncome = dbHelper.getTotalIncome()
            val latestExpense = expenseDbHelper.getLatestExpense()
            val balance = totalIncome - latestExpense

            Log.d(TAG, "Total Income: R$totalIncome")
            Log.d(TAG, "Latest Expense: R$latestExpense")
            Log.d(TAG, "Calculated Balance: R$balance")

            totalIncomeText.text = "Total Balance: R%.2f".format(balance)

            handler.postDelayed(this, updateInterval)
        }
    }
    //saves the instances to the text blocks to display them
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_balance)
        Log.d(TAG, "onCreate called")

        dbHelper = IncomeDatabaseHelper(this)
        expenseDbHelper = BudgetDatabaseHelper(this)

        totalIncomeText = findViewById(R.id.text_total_income)
        recyclerViewIncome = findViewById(R.id.recycler_income_history)
        recyclerViewExpenses = findViewById(R.id.recycler_expense_history)

        val incomeList = dbHelper.getAllIncomeHistory()
        Log.d(TAG, "Loaded ${incomeList.size} income records")

        incomeAdapter = IncomeHistoryAdapter(incomeList)
        recyclerViewIncome.layoutManager = LinearLayoutManager(this)
        recyclerViewIncome.adapter = incomeAdapter

        val expenseList = expenseDbHelper.getAllExpenses()
        Log.d(TAG, "Loaded ${expenseList.size} expense records")

        expenseAdapter = ExpenseHistoryAdapter(expenseList)
        recyclerViewExpenses.layoutManager = LinearLayoutManager(this)
        recyclerViewExpenses.adapter = expenseAdapter


        //bottom navigation to different screens
        // Bottom Navigation setup adapted from Android developer documentation and common practice
    // Source: https://developer.android.com/reference/com/google/android/material/bottomnavigation/BottomNavigationView
    // This code handles item selection from the BottomNavigationView and launches the corresponding Activity

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                //navigates to income screen
                R.id.nav_income -> {
                    Log.d(TAG, "Navigating to IncomeActivity")
                    startActivity(Intent(this, IncomeActivity::class.java))
                    true
                }
                //navigates to home screen
                R.id.nav_home -> {
                    Log.d(TAG, "Navigating to StarterPageActivity")
                    startActivity(Intent(this, HomeScreenActivity::class.java))
                    true
                }
                //navigation to add expenses
                R.id.nav_add -> {
                    Log.d(TAG, "Navigating to AddExpensesActivity")
                    startActivity(Intent(this, AddExpensesActivity::class.java))
                    true
                }
                //navigation to menu nav
                R.id.nav_open_menu -> {
                    Log.d(TAG, "Navigating to MenuActivity")
                    startActivity(Intent(this, MenuActivity::class.java))
                    true
                }
                else -> {
                    Log.w(TAG, "Unknown navigation item selected")
                    false
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called - starting balance updater")
        handler.post(incomeUpdater)
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause called - stopping balance updater")
        handler.removeCallbacks(incomeUpdater)
    }
}
