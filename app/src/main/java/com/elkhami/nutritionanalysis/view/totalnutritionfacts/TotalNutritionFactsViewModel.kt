package com.elkhami.nutritionanalysis.view.totalnutritionfacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elkhami.nutritionanalysis.data.model.IngredientsRequest
import com.elkhami.nutritionanalysis.data.model.Nutrient
import com.elkhami.nutritionanalysis.data.repository.Repository
import com.elkhami.nutritionanalysis.other.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by A.Elkhami on 01,October,2021
 */
@HiltViewModel
class TotalNutritionFactsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private var _totalNutritionDataList = MutableLiveData<List<Nutrient>>()
    val totalNutritionDataList: LiveData<List<Nutrient>> = _totalNutritionDataList

    private var _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> = _loadingState

    private var _errorMessageType = MutableLiveData<String>()
    val errorMessageType: LiveData<String> = _errorMessageType

    var isDataRetrieved = false

    fun getTotalNutritionalFacts(ingredientsList: List<String>) {

        if (!isDataRetrieved) {
            val request = IngredientsRequest(ingredientsList)

            viewModelScope.launch {
                val response = repository.getTotalDailyNutrition(request)

                when (response.status) {
                    Status.SUCCESS -> {
                        response.data?.let { totalNutritionData ->
                            _totalNutritionDataList.value = totalNutritionData
                        }
                        _loadingState.value = false
                    }
                    Status.FAILED -> {
                        _loadingState.value = false

                        response.errorMessage?.let { errorMessage ->
                            _errorMessageType.value = errorMessage
                        }
                    }
                    Status.LOADING -> {
                        _loadingState.value = true
                    }
                }
            }
        }
    }
}