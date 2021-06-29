package com.example.tegeta.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.tegeta.data.model.CurrentCar
import com.example.tegeta.data.repository.CurrentCarsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject internal constructor(
    currentCarsRepository: CurrentCarsRepository
) : ViewModel() {

    val cars: LiveData<List<CurrentCar>> = currentCarsRepository.getCars().asLiveData()
}