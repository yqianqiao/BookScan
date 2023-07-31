package com.example.bookscan.db

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Android Studio.
 * Author: yx
 * Date: 2023/7/7 21:45
 */
@Database(entities = [KuFang::class, SmLog::class, Dalx::class], version = 1)
abstract class BookDataBase : RoomDatabase() {

    abstract fun getKuFangDao(): KuFangDao

    abstract fun getSmLogDao(): SmLogDao

    abstract fun getDalxDao(): DalxDao

}