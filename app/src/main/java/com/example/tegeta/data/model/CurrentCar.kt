package com.example.tegeta.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "current_cars")
data class CurrentCar(
    @PrimaryKey
    @ColumnInfo(name = "number") val number: String,
    @ColumnInfo(name = "add_date") val addDate: Calendar = Calendar.getInstance(),
)