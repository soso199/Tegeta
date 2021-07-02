package com.example.tegeta.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tegeta.data.model.CurrentCar
import com.example.tegeta.data.model.Status
import com.example.tegeta.data.repository.CurrentCarsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCarViewModel @Inject internal constructor(
    private val currentCarsRepository: CurrentCarsRepository
) : ViewModel() {

    fun addCar(number: String, serviceName: String) {
        viewModelScope.launch {
            currentCarsRepository.insertCar(
                CurrentCar(
                    number,
                    serviceName,
                    Status.ADDED.value
                )
            )
        }
    }
}