package com.example.tegeta.ui.home

import androidx.lifecycle.*
import com.example.tegeta.data.model.CurrentCar
import com.example.tegeta.data.model.Status
import com.example.tegeta.data.repository.CurrentCarsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    fun endCurrentCar(car: CurrentCar) {
        viewModelScope.launch(Dispatchers.IO) {
            car.status = Status.FINISHED.value
            currentCarsRepository.updateCar(car)
            withContext(Dispatchers.Main) {
                getCars()
            }
        }
    }
}