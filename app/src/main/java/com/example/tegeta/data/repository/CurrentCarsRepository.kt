package com.example.tegeta.data.repository

import com.example.tegeta.data.dao.CurrentCarsDao
import com.example.tegeta.data.model.CurrentCar
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CurrentCarsRepository @Inject constructor(private val currentCarsDao: CurrentCarsDao) {

    fun getCars() = currentCarsDao.getCars()

    fun getCurrentCars(daySt: Long, dayEt: Long) = currentCarsDao.getCurrentCars(daySt, dayEt)

    fun getCar(carNumber: String) = currentCarsDao.getCar(carNumber)

    suspend fun insertCar(car: CurrentCar) = currentCarsDao.insertCar(car)

    suspend fun updateCar(car: CurrentCar) = currentCarsDao.updateCar(car)
}
