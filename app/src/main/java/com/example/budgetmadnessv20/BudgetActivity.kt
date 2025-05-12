package com.example.budgetmadnessv20

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class BudgetActivity : AppCompatActivity() {

    private lateinit var monthSpinner: Spinner
    private lateinit var minBudgetInput: EditText
    private lateinit var maxBudgetInput: EditText
    private lateinit var budgetInput: EditText
    private lateinit var updateButton: Button
    private lateinit var budgetHandler: BudgetHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget)

        monthSpinner = findViewById(R.id.monthSpinner)
        minBudgetInput = findViewById(R.id.minBudgetInput)
        maxBudgetInput = findViewById(R.id.maxBudgetInput)
        budgetInput = findViewById(R.id.budgetInput)
        updateButton = findViewById(R.id.updateButton)

        // Setup spinner with months
        val months = resources.getStringArray(R.array.months_array)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, months)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        monthSpinner.adapter = adapter

        budgetHandler = BudgetHandler(this)

        updateButton.setOnClickListener {
            val month = monthSpinner.selectedItem.toString()
            val min = minBudgetInput.text.toString()
            val max = maxBudgetInput.text.toString()
            val budget = budgetInput.text.toString()

            if (min.isBlank() || max.isBlank() || budget.isBlank()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                val inserted = budgetHandler.insertBudget(
                    month,
                    min.toDouble(),
                    max.toDouble(),
                    budget.toDouble()
                )

                if (inserted) {
                    Toast.makeText(this, "Budget saved!", Toast.LENGTH_SHORT).show()
                    minBudgetInput.text.clear()
                    maxBudgetInput.text.clear()
                    budgetInput.text.clear()
                } else {
                    Toast.makeText(this, "Error saving budget", Toast.LENGTH_SHORT).show()
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
}
