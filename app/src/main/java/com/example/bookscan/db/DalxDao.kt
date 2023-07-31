package com.example.bookscan.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface DalxDao {

    @Query("SELECT * FROM hkms_dalx")
    suspend fun getAllDalx(): List<Dalx>
}