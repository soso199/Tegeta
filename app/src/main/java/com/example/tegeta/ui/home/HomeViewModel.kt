package com.example.tegeta.ui.home

import androidx.lifecycle.*
import com.example.tegeta.data.model.CurrentCar
import com.example.tegeta.data.repository.CurrentCarsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject internal constructor(
    private val currentCarsRepository: CurrentCarsRepository
) : ViewModel() {

    private val updateRequest = MutableLiveData<Void?>()

    var cars: LiveData<List<CurrentCar>> = Transformations.switchMap(updateRequest) {
        currentCarsRepository.getCurrentCars(
            Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 0)
            }.timeInMillis,
            Calendar.getInstance().timeInMillis
        ).asLiveData()
    }

    fun getCars() {
        updateRequest.value = null
    }
}