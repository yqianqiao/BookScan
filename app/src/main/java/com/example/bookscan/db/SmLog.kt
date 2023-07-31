package com.example.bookscan.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * Created by Android Studio.
 * Author: yx
 * Date: 2023/7/10 22:29
 */
@Entity(tableName = "hkms_smlog")
data class SmLog(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "kfid")
    var kfid: Int = 0,

    @ColumnInfo(name = "kfmc")

    var kfmc: String? = null,

    @ColumnInfo(name = "dalxid")
    var dalxid: Int = 0,

    @ColumnInfo(name = "dalxmc")
    var dalxmc: String = "",

    @ColumnInfo(name = "cjm")

    var cjm: String? = null,

    @ColumnInfo(name = "hh")
    var hh: String = "",

    @ColumnInfo(name = "smsj")

    var smsj: String? = null,

    @ColumnInfo(name = "zbzt")

    var zbzt: Int? = null,

    @ColumnInfo(name = "zbsj")

    var zbsj: String? = null
) 