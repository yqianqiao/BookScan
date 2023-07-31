package com.example.bookscan.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hkms_dalx")
data class Dalx(
    @PrimaryKey()
    @ColumnInfo(name = "modelid")
    val modelid: Int, // 档案类型ID

    @ColumnInfo(name = "dalx")
    val dalx: String?, // 档案类型
)