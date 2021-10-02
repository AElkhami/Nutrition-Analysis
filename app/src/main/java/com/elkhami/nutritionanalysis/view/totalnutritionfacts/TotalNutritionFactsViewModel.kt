package com.elkhami.nutritionanalysis.view.totalnutritionfacts

import androidx.lifecycle.ViewModel
import com.elkhami.nutritionanalysis.data.repository.NutritionAnalysisRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by A.Elkhami on 01,October,2021
 */
@HiltViewModel
class TotalNutritionFactsViewModel @Inject constructor(repository: NutritionAnalysisRepository) :
    ViewModel() {

}