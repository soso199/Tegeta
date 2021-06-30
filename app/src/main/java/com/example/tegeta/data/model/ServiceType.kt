package com.example.tegeta.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "service_type")
data class ServiceType(
    @ColumnInfo(name = "name")
    var name: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var serviceId: Long = 0
}