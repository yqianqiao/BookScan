package com.example.bookscan

import android.app.Application
import androidx.room.Room
import com.example.bookscan.db.BookDataBase
import com.example.bookscan.db.DatabaseHelper
import com.hjq.toast.Toaster

/**
 * Created by Android Studio.
 * Author: yx
 * Date: 2023/7/8 22:02
 */
class App : Application() {
    lateinit var db: BookDataBase
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext, BookDataBase::class.java, "book_scan.db"
        ).addCallback(DatabaseHelper(this, "kufang.sql"))
            .addCallback(DatabaseHelper(this,"dalx.sql"))
            .build()

        Toaster.init(this);
    }
}