package com.example.bookscan.db

import androidx.annotation.Nullable
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by Android Studio.
 * Author: yx
 * Date: 2023/7/8 21:59
 */
@Dao
interface KuFangDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKuFangEntity(entity: KuFang)

    @Query("SELECT * FROM hkms_kufang WHERE STATUS='0'")
    suspend fun getAllKuFangEntity(): List<KuFang>

    @Query("SELECT * FROM hkms_kufang WHERE KFID = :kfid")
    suspend fun getKuFangById(kfid: Int): KuFang
}