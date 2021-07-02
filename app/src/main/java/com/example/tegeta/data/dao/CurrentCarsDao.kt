package com.example.tegeta.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.tegeta.data.model.CurrentCar
import kotlinx.coroutines.flow.Flow


@Dao
interface CurrentCarsDao {
    @Query("SELECT * FROM current_cars ORDER BY add_date")
    fun getCars(): Flow<List<CurrentCar>>

    @Query("SELECT * FROM current_cars WHERE add_date BETWEEN :daySt AND :dayEt ORDER BY add_date")
    fun getCurrentCars(daySt: Long, dayEt: Long): Flow<List<CurrentCar>>

    @Query("SELECT * FROM current_cars WHERE number = :carNumber")
    fun getCar(carNumber: String): Flow<CurrentCar>

    @Insert
    suspend fun insertCar(car: CurrentCar): Long

    @Update(onConflict = REPLACE)
    fun updateCar(car: CurrentCar)
}