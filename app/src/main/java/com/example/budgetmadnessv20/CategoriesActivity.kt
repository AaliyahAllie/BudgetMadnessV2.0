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
    private lateinit var deleteCategoryButton:Button
    private lateinit var adapter:ArrayAdapter<String>
    private lateinit var categories:MutableList<String>
    private var selectedCategory:String? = null  // Track selected category

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_categories)

        dbHelper = BudgetDatabaseHelper(this)

        categoryListView = findViewById(R.id.categoryListView)
        newCategoryInput = findViewById(R.id.newCategoryInput)
        addCategoryButton = findViewById(R.id.addCategoryButton)
        deleteCategoryButton = findViewById(R.id.deleteCategoryButton)

        //LOAD CATEGORIES INTO LIST
        categories = dbHelper.getAllCategories().toMutableList()
        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,categories)
        categoryListView.adapter = adapter

        //HANDLE ITEM INTO LISTVIEW
        categoryListView.setOnItemClickListener { _, _, position, _ ->
            selectedCategory = categories[position]
            Toast.makeText(this, "$selectedCategory selected", Toast.LENGTH_SHORT).show()
        }

        //ADD CATEGORY BUTTON

        //DELETE CATEGORY BUTTON

        //BOTTOM NAVIGATION



    }
}

