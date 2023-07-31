package com.example.bookscan.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by Android Studio.
 * Author: yx
 * Date: 2023/7/8 19:38
 */
@Entity(tableName = "hkms_kufang")
data class KuFang(
    @PrimaryKey(true)
    @ColumnInfo(name = "KFID")
    val kfid: Int, // 库房ID

    @ColumnInfo(name = "KFPID")
    val kfpid: Int?, // 库房PID

    @ColumnInfo(name = "AREA")
    val area: String?, // 区域

    @ColumnInfo(name = "KFTYPE")
    val kftype: Int?, // 1 档案柜 2 密集架

    @ColumnInfo(name = "NAME")
    val name: String?, // 库房名称

    @ColumnInfo(name = "LJC")
    val ljc: String?, // 列节层

    @ColumnInfo(name = "CREATETIME")
    val createTime: Int?, // 创建时间

    @ColumnInfo(name = "CREATEUSER")
    val createUser: String?, // 创建人

    @ColumnInfo(name = "FJH")
    val fjh: String?, // 房间号

    @ColumnInfo(name = "ADDR")
    val addr: String?, // 地址

    @ColumnInfo(name = "DEPTID")
    val deptid: Int?, // 部门ID

    @ColumnInfo(name = "QZH")
    val qzh: String?, // 全宗号

    @ColumnInfo(name = "REMARK")
    val remark: String?, // 备注

    @ColumnInfo(name = "STATUS")
    val status: String // 状态
)