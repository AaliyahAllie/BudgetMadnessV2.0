package com.example.budgetmadnessv20

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class BudgetViewActivity : AppCompatActivity() {

    private lateinit var viewMonthSpinner: Spinner
    private lateinit var viewButton: Button
    private lateinit var budgetDetailsText: TextView
    private lateinit var budgetHandler: BudgetHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget_view)

        viewMonthSpinner = findViewById(R.id.viewMonthSpinner)
        viewButton = findViewById(R.id.viewButton)
        budgetDetailsText = findViewById(R.id.budgetDetailsText)

        val months = resources.getStringArray(R.array.months_array)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, months)
        viewMonthSpinner.adapter = adapter

        budgetHandler = BudgetHandler(this)

        viewButton.setOnClickListener {
            val selectedMonth = viewMonthSpinner.selectedItem.toString()
            val budget = budgetHandler.getBudgetForMonth(selectedMonth)

            if (budget != null) {
                budgetDetailsText.text = """
                    Month: $selectedMonth
                    Min Budget: R${budget.min}
                    Max Budget: R${budget.max}
                    Entered Budget: R${budget.entered}
                """.trimIndent()
            } else {
                budgetDetailsText.text = "No budget found for $selectedMonth."
            }
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


