package com.elkhami.nutritionanalysis.data.stub

import com.elkhami.nutritionanalysis.data.model.NutritionalFactsResponse
import com.elkhami.nutritionanalysis.data.model.TotalNutrients
import com.elkhami.nutritionanalysis.data.model.nutrition.*

/**
 * Created by A.Elkhami on 30,September,2021
 */
object NutritionFactsStub {

    val nutritionalFactsStub = NutritionalFactsResponse(
        "",
        "",
        "",
        "1",
        15,
        TotalNutrients(
            CA("", 0.0, ""),
            CHOCDF("", 0.0, ""),
            CHOLE("", 0.0, ""),
            ENERCKCAL("", 0.0, ""),
            FAT("", 0.0, ""),
            FE("", 0.0, ""),
            K("", 0.0, ""),
            NA("", 0.0, ""),
            PROCNT("", 0.0, ""),
            VITARAE("", 0.0, ""),
            VITB12("", 0.0, ""),
            VITB6A("", 0.0, ""),
            VITC("", 0.0, ""),
            VITD("",0.0,""),
            TOCPHA("",0.0,""),
            VITK1("",0.0,""),

        ),
        0,
        0
    )


    val nutritionBreakdownList = listOf(nutritionalFactsStub)
}