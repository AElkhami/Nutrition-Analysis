package com.elkhami.nutritionanalysis.data.model

import com.elkhami.nutritionanalysis.data.model.nutrition.*

data class TotalNutrients(
    val CA: CA,
    val CHOCDF: CHOCDF,
    val CHOLE: CHOLE,
    val ENERC_KCAL: ENERCKCAL,
    val FAT: FAT,
    val FE: FE,
    val K: K,
    val NA: NA,
    val PROCNT: PROCNT,
    val VITA_RAE: VITARAE,
    val VITB12: VITB12,
    val VITB6A: VITB6A,
    val VITC: VITC,
    val VITD: VITD,
    val TOCPHA: TOCPHA,
    val VITK1: VITK1
){
    fun getTotalNutrientsList()
    : ArrayList<Nutrient>{
        val nutrientsList = ArrayList<Nutrient>()

        nutrientsList.add(mapNutrient(CA))
        nutrientsList.add(mapNutrient(CHOCDF))
        nutrientsList.add(mapNutrient(CHOLE))
        nutrientsList.add(mapNutrient(ENERC_KCAL))
        nutrientsList.add(mapNutrient(FAT))
        nutrientsList.add(mapNutrient(FE))
        nutrientsList.add(mapNutrient(NA))
        nutrientsList.add(mapNutrient(CA))
        nutrientsList.add(mapNutrient(PROCNT))
        nutrientsList.add(mapNutrient(VITA_RAE))
        nutrientsList.add(mapNutrient(VITB12))
        nutrientsList.add(mapNutrient(VITB6A))
        nutrientsList.add(mapNutrient(VITC))
        nutrientsList.add(mapNutrient(VITD))
        nutrientsList.add(mapNutrient(TOCPHA))
        nutrientsList.add(mapNutrient(VITK1))

        return nutrientsList
    }

    private fun mapNutrient(nutrient: Nutrient): Nutrient{
        val newNutrient = Nutrient()
        newNutrient.label = nutrient.label
        newNutrient.unit = nutrient.unit
        newNutrient.quantity = nutrient.quantity
        return newNutrient
    }
}