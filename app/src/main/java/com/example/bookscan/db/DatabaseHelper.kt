package com.example.bookscan.db

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


/**
 * Created by Android Studio.
 * Author: yx
 * Date: 2023/7/10 22:03
 */
class DatabaseHelper(private val context: Context, private val fileName: String) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        Log.e("yx===", "stringBuilder :" );
        try {
            val inputStream: InputStream = context.assets.open(fileName)
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))

            val stringBuilder = StringBuilder()
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                if (line!!.trim().isNotEmpty()) {
                    stringBuilder.append(line)
                    stringBuilder.append("\n")
                    if (line!!.endsWith(";")) {
                        db.execSQL(stringBuilder.toString())
                        stringBuilder.clear()
                    }
                }
            }

            bufferedReader.close()
            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}