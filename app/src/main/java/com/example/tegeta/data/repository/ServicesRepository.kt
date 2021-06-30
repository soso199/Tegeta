package com.example.tegeta.data.repository

import com.example.tegeta.data.dao.ServicesDao
import com.example.tegeta.data.model.ServiceType
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ServicesRepository @Inject constructor(private val servicesDao: ServicesDao) {

    fun getServices() = servicesDao.getServices()

    fun getService(serviceId: Long) = servicesDao.getService(serviceId)

    suspend fun insertService(service: ServiceType) = servicesDao.insertService(service)
}
