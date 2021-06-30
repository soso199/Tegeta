package com.example.tegeta.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.tegeta.data.model.CurrentCar
import com.example.tegeta.data.model.Status
import com.example.tegeta.data.repository.CurrentCarsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject internal constructor(
    private val currentCarsRepository: CurrentCarsRepository
) : ViewModel() {

    val cars: LiveData<List<CurrentCar>> = currentCarsRepository.getCars().asLiveData()

    fun addTempCar() {
        viewModelScope.launch {
            currentCarsRepository.insertCar(
                CurrentCar(
                    "AA-555-AA",
                    "დიაგნოსტიკა",
                    Status.ADDED.value
                )
            )
        }
    }
}