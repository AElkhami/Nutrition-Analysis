package com.elkhami.nutritionanalysis.data.stub

import com.elkhami.nutritionanalysis.data.model.NutritionalFactsResponse
import com.elkhami.nutritionanalysis.data.model.TotalNutrients
import com.elkhami.nutritionanalysis.data.model.nutrition.*

/**
 * Created by A.Elkhami on 30,September,2021
 */
object NutritionFactsStub {

    val CA = CA().apply {
        label = ""
        unit = ""
        quantity = 0.0
    }

    val CHOCDF = CHOCDF().apply {
        label = ""
        unit = ""
        quantity = 0.0
    }

    val CHOLE = CHOLE().apply {
        label = ""
        unit = ""
        quantity = 0.0
    }

    val ENERCKCAL = ENERCKCAL().apply {
        label = ""
        unit = ""
        quantity = 0.0
    }

    val FAT = FAT().apply {
        label = ""
        unit = ""
        quantity = 0.0
    }

    val FE = FE().apply {
        label = ""
        unit = ""
        quantity = 0.0
    }

    val K = K().apply {
        label = ""
        unit = ""
        quantity = 0.0
    }

    val NA = NA().apply {
        label = ""
        unit = ""
        quantity = 0.0
    }

    val PROCNT = PROCNT().apply {
        label = ""
        unit = ""
        quantity = 0.0
    }

    val VITARAE = VITARAE().apply {
        label = ""
        unit = ""
        quantity = 0.0
    }

    val VITB12 = VITB12().apply {
        label = ""
        unit = ""
        quantity = 0.0
    }

    val VITB6A = VITB6A().apply {
        label = ""
        unit = ""
        quantity = 0.0
    }

    val VITC = VITC().apply {
        label = ""
        unit = ""
        quantity = 0.0
    }

    val VITD = VITD().apply {
        label = ""
        unit = ""
        quantity = 0.0
    }

    val TOCPHA = TOCPHA().apply {
        label = ""
        unit = ""
        quantity = 0.0
    }

    val VITK1 = VITK1().apply {
        label = ""
        unit = ""
        quantity = 0.0
    }

    val totalNutrientsStub = TotalNutrients(
        CA,
        CHOCDF,
        CHOLE,
        ENERCKCAL,
        FAT,
        FE,
        K,
        NA,
        PROCNT,
        VITARAE,
        VITB12,
        VITB6A,
        VITC,
        VITD,
        TOCPHA,
        VITK1,
    )

    val nutritionalFactsStub = NutritionalFactsResponse(
        "",
        "",
        "",
        "1",
        15,
        totalNutrientsStub,
        0,
        0
    )


    val nutritionBreakdownList = listOf(nutritionalFactsStub)
}