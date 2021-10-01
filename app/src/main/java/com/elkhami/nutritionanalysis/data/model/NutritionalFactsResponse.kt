package com.elkhami.nutritionanalysis.data.model

data class NutritionalFactsResponse(
    var foodName: String,
    var weight: String,
    var unit: String,
    val uri: String,
    val calories: Int,
    val totalNutrients: TotalNutrients,
    var totalWeight: Int,
    val yield: Int
)