package com.example.assignmentsnapworktask.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.assignmentsnapworktask.model.DateInfo

@Dao
interface DateInfoInterface {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(dateInfo: DateInfo)

//    @Query("SELECT * FROM DateInfo")
//    suspend fun getTextByDate(): List<DateInfo>

//    @Query("SELECT * FROM DateInfo")
//    suspend fun getTextByDate(date: String): DateInfo?

    @Query("SELECT * FROM dateInfo WHERE date = :date")
    suspend fun getTextByDate(date: String): DateInfo?
    }
