package com.example.tegeta.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tegeta.data.model.ServiceType
import kotlinx.coroutines.flow.Flow

@Dao
interface ServicesDao {
    @Query("SELECT * FROM service_type ORDER BY name")
    fun getServices(): Flow<List<ServiceType>>

    @Query("SELECT * FROM service_type WHERE id = :serviceId")
    fun getService(serviceId: Long): Flow<ServiceType>

    @Insert
    suspend fun insertService(service: ServiceType): Long
}