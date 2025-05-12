package com.example.budgetmadnessv20

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.widget.*



class CategoriesActivity : AppCompatActivity() {

    private lateinit var dbHelper:BudgetDatabaseHelper
    private lateinit var categoryListView: ListView
    private lateinit var newCategoryInput: EditText
    private lateinit var addCategoryButton: Button
    private lateinit var deleteCategory:Button
    private lateinit var adapter:ArrayAdapter<String>
    private lateinit var categories:MutableList<String>
    private var selectedCategory:String? = null  // Track selected category

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_categories)


    }
}

