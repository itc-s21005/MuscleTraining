package jp.ac.it_college.std.s20001.muscletraining

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME,
    null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "record.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sb = """
            CREATE TABLE record (
            menu TEXT,
            date TEXT
            )
        """.trimIndent()

        db!!.execSQL(sb)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}
}