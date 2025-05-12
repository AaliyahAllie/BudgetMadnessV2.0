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

    private var selectDate: String = ""

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


        //DATE PICKER
        //Function logic adapted from:[https://www.geeksforgeeks.org/datepicker-in-kotlin/]


        //ADD EXPENSE
        //Function logic adapted from:[https://www.geeksforgeeks.org/android-sqlite-database-in-kotlin/]


        //BOTTOM NAVIGATION

        }

}