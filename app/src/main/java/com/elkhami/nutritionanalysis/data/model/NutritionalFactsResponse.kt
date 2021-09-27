package com.elkhami.nutritionanalysis.data.model

data class NutritionalFactsResponse(
    val calories: Int,
    val totalNutrients: TotalNutrients,
    val totalWeight: Int,
    val yield: Int
)