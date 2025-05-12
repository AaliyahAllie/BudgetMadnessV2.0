package com.example.budgetmadnessv20

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class ExpenseViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_expense_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //BOTTOM NAV
        //navigation to other screens
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                //navigates to income screen
                R.id.nav_income -> {
                    startActivity(Intent(this, IncomeActivity::class.java))
                    true
                }
                //navigates to home screen
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeScreenActivity::class.java))
                    true
                }
                //allows user to add expenses on redirect
                R.id.nav_add -> {
                    startActivity(Intent(this, AddExpensesActivity::class.java))
                    true
                }
                R.id.nav_open_menu -> {
                    startActivity(Intent(this, MenuActivity::class.java))
                    true
                }

                else -> false
            }
        }
    }
}