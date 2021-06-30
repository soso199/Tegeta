package com.example.tegeta.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "current_cars")
data class CurrentCar(
    @ColumnInfo(name = "number") val number: String,
    @ColumnInfo(name = "service_name") val serviceName: String,
    @ColumnInfo(name = "status") val status: Int,
    @ColumnInfo(name = "add_date") val addDate: Calendar = Calendar.getInstance(),
    @ColumnInfo(name = "amount") val amount: Double? = null,
    @ColumnInfo(name = "end_date") val endDate: Calendar = Calendar.getInstance(),
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}