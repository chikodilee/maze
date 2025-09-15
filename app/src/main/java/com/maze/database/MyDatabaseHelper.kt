package com.maze.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.maze.data.UserData


class MyDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val query =
            "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, " +
                    "$CAR_MAKE_MODEL TEXT, $LICENCE_PLATE_NUMBER TEXT, $USER_SIGNED_UP INTEGER)"
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertUserInformation(userData: UserData, context: Context) {
        val sqlInstance = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_ID, userData._id)
            put(CAR_MAKE_MODEL, userData.carMakeModel)
            put(LICENCE_PLATE_NUMBER, userData.licencePlateNumber)
            put(USER_SIGNED_UP, userData.userSignedUp)
        }

        val databaseInsertResponse = sqlInstance.insert(TABLE_NAME, null, contentValues)
        if (databaseInsertResponse.toInt() == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Sign Up Successful", Toast.LENGTH_SHORT).show()
        }
    }

    fun getAllUsers(): Cursor {
        val dbRead = readableDatabase
        return dbRead.rawQuery(
            "SELECT * FROM $TABLE_NAME", null
        )
    }

    fun findUserByLicensePlate(licencePlateNumber: String): Cursor {
        val dbRead = readableDatabase
        return dbRead.rawQuery(
            "SELECT * FROM $TABLE_NAME WHERE $LICENCE_PLATE_NUMBER=?",
            arrayOf(licencePlateNumber)
        )
    }

    fun deleteAllUsers() {
        val dbWrite = writableDatabase
        dbWrite.execSQL("DELETE FROM $TABLE_NAME")
        dbWrite.close()
    }

    fun deleteUserByLicencePlate(licencePlateNumber: String) {
        val dbWrite = writableDatabase
        dbWrite.delete(
            TABLE_NAME,
            "$LICENCE_PLATE_NUMBER=?",
            arrayOf(licencePlateNumber)
        )
        dbWrite.close()
    }

    fun getLastRowID(): Cursor {
        val dbRead = readableDatabase
        return dbRead.rawQuery("SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_ID DESC LIMIT 1", null)
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "AppUsers.db"
        private const val TABLE_NAME = "users_data"

        //Columns in the table named "users_data"
        private const val COLUMN_ID = "_id"
        private const val CAR_MAKE_MODEL = "car_make"
        private const val LICENCE_PLATE_NUMBER = "licence_plate_number"
        private const val USER_SIGNED_UP = "user_signed_up"

    }
}