package com.example.bookscan.db

import androidx.annotation.Nullable
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.ArrayList

/**
 * Created by Android Studio.
 * Author: yx
 * Date: 2023/7/10 22:33
 */
@Dao
interface SmLogDao {
    @Insert
    suspend fun insertSmLog(entity: SmLog)

    @Query("SELECT * FROM hkms_smlog ORDER BY ID DESC")
    suspend fun getAllSmLog(): List<SmLog>

    @Delete
    suspend fun deleteSmLog(entity: SmLog)

    @Query("SELECT * FROM hkms_smlog WHERE KFID=:kfid  ORDER BY ID DESC")
    suspend fun getKuFangList(kfid: Int): List<SmLog>



}