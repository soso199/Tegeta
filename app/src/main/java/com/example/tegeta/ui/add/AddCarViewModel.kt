package com.example.tegeta.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.tegeta.data.model.CurrentCar
import com.example.tegeta.data.model.ServiceType
import com.example.tegeta.data.model.Status
import com.example.tegeta.data.repository.CurrentCarsRepository
import com.example.tegeta.data.repository.ServicesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCarViewModel @Inject internal constructor(
    private val currentCarsRepository: CurrentCarsRepository,
    private val servicesRepository: ServicesRepository
) : ViewModel() {

    var chosenService: String = ""

    val services: LiveData<List<ServiceType>> =
        servicesRepository.getServices().asLiveData()

    fun addCar(number: String) {
        viewModelScope.launch {
            currentCarsRepository.insertCar(
                CurrentCar(
                    number,
                    chosenService,
                    Status.ADDED.value
                )
            )
        }
    }
}