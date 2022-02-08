package com.example.chess

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DataBaseHelper internal constructor(context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {

        updateMyDatabase(db, 0, DB_VERSION)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        updateMyDatabase(db, oldVersion, newVersion)
    }

    private fun updateMyDatabase(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 4) {
            db.execSQL(
                "CREATE TABLE GAME (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "NOTE TEXT, "
                        + "WINNER TEXT); "
            )
            insertGame(db, "e2:e4; e7:e5; a2:a4; a7:a5", "White")
            insertGame(db, "d2:e3; e7:e5; a2:a4; a7:a5", "Black")
            insertGame(db, "e2:e4; h7:h5; a2:a4; a7:a5", "Black")
            insertGame(db, "e2:e4; e7:e5; a2:a4; a7:a5", "White")
        }
    }

    companion object {
        private const val DB_NAME = "notation" // the name of our database
        private const val DB_VERSION = 5 // the version of the database

        private fun insertGame(
            db: SQLiteDatabase, description: String,
            winner: String
        ) {
            val noteValues = ContentValues()
            noteValues.put("NOTE", description)
            noteValues.put("WINNER", winner)
            db.insert("GAME", null, noteValues)
        }
    }
}

