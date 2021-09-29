package com.elkhami.nutritionanalysis.data.model

data class NutritionalFactsResponse(
    val foodName: String,
    val weight: String,
    val unit: String,
    val uri: String,
    val calories: Int,
    val totalNutrients: TotalNutrients,
    val totalWeight: Int,
    val yield: Int
)