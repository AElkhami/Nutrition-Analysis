package com.elkhami.nutritionanalysis.view.nutritionsbreakdown

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elkhami.nutritionanalysis.data.model.NutritionalFactsResponse
import com.elkhami.nutritionanalysis.data.repository.Repository
import com.elkhami.nutritionanalysis.other.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by A.Elkhami on 29,September,2021
 */
@HiltViewModel
class NutritionBreakdownViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private var _nutritionBreakdownList = MutableLiveData<List<NutritionalFactsResponse>>()
    val nutritionBreakdownList: LiveData<List<NutritionalFactsResponse>> = _nutritionBreakdownList

    private var _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> = _loadingState

    private var _errorMessageType = MutableLiveData<String>()
    val errorMessageType: LiveData<String> = _errorMessageType

    private val responseList: ArrayList<NutritionalFactsResponse> = ArrayList()

    fun getNutritionalDataForIngredients(ingredientsList: List<String>) {
        viewModelScope.launch {
            repository.processAllNutritionRequests(ingredientsList).collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.let { nutritionFactsResponse ->
                            responseList.add(nutritionFactsResponse)
                            _nutritionBreakdownList.value = responseList
                        }
                        _loadingState.value = false
                    }
                    Status.FAILED -> {
                        _loadingState.value = false

                        it.errorMessage?.let { errorMessage ->
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

    fun navigationComplete() {
        _nutritionBreakdownList.value = null
    }

}