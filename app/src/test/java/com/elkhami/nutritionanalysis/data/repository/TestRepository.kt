package com.elkhami.nutritionanalysis.data.repository

import com.elkhami.nutritionanalysis.data.model.IngredientsRequest
import com.elkhami.nutritionanalysis.data.model.NutritionalFactsResponse
import com.elkhami.nutritionanalysis.data.model.TotalNutrients
import com.elkhami.nutritionanalysis.data.model.nutrition.*
import com.elkhami.nutritionanalysis.other.Constants.NETWORK_ERROR
import com.elkhami.nutritionanalysis.other.Constants.UNKNOWN_ERROR
import com.elkhami.nutritionanalysis.other.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created by A.Elkhami on 27,September,2021
 */
class TestRepository : Repository {

    private var shouldReturnNetworkError = false
    private var isResponseSuccessful = false

    private val nutritionalFactsMock = NutritionalFactsResponse(
        "carrot",
        "50",
        "g",
        "1",
        0,
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
            VITC("", 0.0, "")
        ),
        0,
        0
    )


    override suspend fun getNutritionForItem(ingr: String)
            : Resource<NutritionalFactsResponse> {
        return if (shouldReturnNetworkError) {
            if (isResponseSuccessful) {
                Resource.Success(nutritionalFactsMock)
            } else {
                Resource.Fail(errorMessage = UNKNOWN_ERROR)
            }
        } else {
            Resource.Fail(errorMessage = NETWORK_ERROR)
        }
    }

    override suspend fun processAllNutritionRequests(ingrs: List<String>)
            : Flow<Pair<String, Resource<NutritionalFactsResponse>>> {
        TODO("Not yet implemented")
    }


    override suspend fun getNutritionForList(request: IngredientsRequest)
            : Resource<NutritionalFactsResponse> {
        return if (shouldReturnNetworkError) {
            if (isResponseSuccessful) {
                Resource.Success(nutritionalFactsMock)
            } else {
                Resource.Fail(errorMessage = UNKNOWN_ERROR)
            }
        } else {
            Resource.Fail(errorMessage = NETWORK_ERROR)
        }
    }

}