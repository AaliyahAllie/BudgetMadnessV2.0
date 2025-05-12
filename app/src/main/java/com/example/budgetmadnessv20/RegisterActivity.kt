package com.example.budgetmadnessv20

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private val TAG = "RegisterActivity"
//captures users information logs it and stores it into the databse
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        Log.d(TAG, "RegisterActivity started")
        logToFile("RegisterActivity started")

        dbHelper = DatabaseHelper(this)
//captures user information into database
        val username = findViewById<EditText>(R.id.usernameInput)
        val password = findViewById<EditText>(R.id.passwordInput)
        val firstName = findViewById<EditText>(R.id.firstNameInput)
        val lastName = findViewById<EditText>(R.id.lastNameInput)
        val email = findViewById<EditText>(R.id.emailInput)
        val phone = findViewById<EditText>(R.id.phoneInput)
        val registerBtn = findViewById<Button>(R.id.registerUserBtn)
//button saves data
        registerBtn.setOnClickListener {
            val user = username.text.toString().trim()
            val pass = password.text.toString().trim()
            val first = firstName.text.toString().trim()
            val last = lastName.text.toString().trim()
            val mail = email.text.toString().trim()
            val phoneNum = phone.text.toString().trim()

            val logMessage = "Register clicked - Username: $user, First Name: $first, Last Name: $last, Email: $mail, Phone: $phoneNum"
            Log.d(TAG, logMessage)
            logToFile(logMessage)
//if data is successfully stored it will redirect to users login station
            if (user.isNotEmpty() && pass.isNotEmpty() && first.isNotEmpty() && last.isNotEmpty() && mail.isNotEmpty() && phoneNum.isNotEmpty()) {
                dbHelper.addUser(user, pass, first, last, mail, phoneNum)

                val successMessage = "Registration successful for user: $user"
                Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show()
                Log.i(TAG, successMessage)
                logToFile(successMessage)

                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                val errorMessage = "Registration failed - one or more fields are empty"
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                Log.w(TAG, errorMessage)
                logToFile(errorMessage)
            }
        }
    }

    private fun logToFile(message: String) {
        try {
            val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
            val logFile = File(filesDir, "app_log.txt")
            logFile.appendText("[$timestamp] $message\n")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to write to log file", e)
        }
    }
}