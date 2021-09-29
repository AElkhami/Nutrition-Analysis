package com.elkhami.nutritionanalysis.view.nutritionsbreakdown

import androidx.lifecycle.ViewModel
import com.elkhami.nutritionanalysis.data.remote.NutritionAnalysisAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by A.Elkhami on 29,September,2021
 */
@HiltViewModel
class NutritionBreakdownViewModel @Inject constructor(
    val api: NutritionAnalysisAPI
): ViewModel() {


}