package com.example.budgetmadnessv20

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BudgetHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_MONTH TEXT,
                $COLUMN_MIN REAL,
                $COLUMN_MAX REAL,
                $COLUMN_BUDGET REAL
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertBudget(month: String, min: Double, max: Double, budget: Double): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_MONTH, month)
            put(COLUMN_MIN, min)
            put(COLUMN_MAX, max)
            put(COLUMN_BUDGET, budget)
        }
        val result = db.insert(TABLE_NAME, null, values)
        return result != -1L
    }

    companion object {
        private const val DATABASE_NAME = "budget.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "budget"
        private const val COLUMN_ID = "id"
        private const val COLUMN_MONTH = "month"
        private const val COLUMN_MIN = "min_budget"
        private const val COLUMN_MAX = "max_budget"
        private const val COLUMN_BUDGET = "monthly_budget"
    }
}
