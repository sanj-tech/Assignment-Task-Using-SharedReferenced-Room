package com.example.assignmentsnapworktask.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class DateInfo(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val date: String,
    val text: String
)