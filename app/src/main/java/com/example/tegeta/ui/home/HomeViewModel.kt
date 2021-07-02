package com.example.tegeta.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.tegeta.data.model.CurrentCar
import com.example.tegeta.data.repository.CurrentCarsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject internal constructor(
    private val currentCarsRepository: CurrentCarsRepository
) : ViewModel() {

    val cars: LiveData<List<CurrentCar>> =
        currentCarsRepository.getCurrentCars(
            Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 0)
            }.timeInMillis,
            Calendar.getInstance().timeInMillis
        ).asLiveData()

}