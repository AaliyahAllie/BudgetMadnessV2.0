package com.example.budgetmadnessv20

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import  java.util.*
import android.app.DatePickerDialog
import android.content.Intent
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.app.AlertDialog
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.core.content.FileProvider
import java.io.File

class AddExpensesActivity : AppCompatActivity() {

    private lateinit var expenseNameInput: EditText
    private lateinit var expenseAmountInput: EditText
    private lateinit var paymentMethodInput: EditText
    private lateinit var categorySpinner: Spinner
    private lateinit var selectDateButton: Button
    private lateinit var uploadReceiptButton: Button
    private lateinit var addExpenseButton: Button
    private val PICK_IMAGE_REQUEST = 1
    private val REQUEST_IMAGE_CAPTURE = 2
    private var selectedImageBytes: ByteArray? = null
    private var imageUri: Uri? = null

    private var selectedDate:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_expenses)

        val dbHelper = BudgetDatabaseHelper(this)

        //LINK VIEWS
        expenseNameInput = findViewById(R.id.expenseNameInput)
        expenseAmountInput = findViewById(R.id.expenseAmountInput)
        paymentMethodInput = findViewById(R.id.paymentMethodInput)
        categorySpinner = findViewById(R.id.categorySpinner)
        selectDateButton = findViewById(R.id.selectDateButton)
        uploadReceiptButton = findViewById(R.id.uploadReceiptButton)
        addExpenseButton = findViewById(R.id.addExpenseButton)

        //SPINNER SETUP
        //function logic adapted from:[https://www.geeksforgeeks.org/spinner-in-kotlin/]
        val categories = dbHelper.getAllCategories()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = adapter

        //DATE PICKER
        //Function logic adapted from:[https://www.geeksforgeeks.org/datepicker-in-kotlin/]
        selectDateButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, y, m, d ->
                var selectedDate = "$y-${m + 1}-$d"
                selectDateButton.text = selectedDate
            }, year, month, day)

            datePickerDialog.show()
        }


        //ADD EXPENSE
        //Function logic adapted from:[https://www.geeksforgeeks.org/android-sqlite-database-in-kotlin/]



        //BOTTOM NAVIGATION
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_income -> {
                    startActivity(Intent(this, IncomeActivity::class.java))
                    true
                }

                R.id.nav_home -> {
                    startActivity(Intent(this, StarterPageActivity::class.java))
                    true
                }

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

    private fun clearInputs() {

        expenseNameInput.text.clear()
        expenseAmountInput.text.clear()
        paymentMethodInput.text.clear()
        categorySpinner.setSelection(0)
        selectDateButton.text = "Select Date"
        selectedDate = ""
    }
}
