package com.elkhami.nutritionanalysis.data.repository

import com.elkhami.nutritionanalysis.data.model.IngredientsRequest
import com.elkhami.nutritionanalysis.data.model.Nutrient
import com.elkhami.nutritionanalysis.data.model.NutritionalFactsResponse
import com.elkhami.nutritionanalysis.data.stub.NutritionFactsStub
import com.elkhami.nutritionanalysis.data.stub.NutritionFactsStub.totalNutrientsStub
import com.elkhami.nutritionanalysis.other.Constants.NETWORK_ERROR
import com.elkhami.nutritionanalysis.other.Constants.UNKNOWN_ERROR
import com.elkhami.nutritionanalysis.other.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by A.Elkhami on 27,September,2021
 */
class TestRepository : Repository {

    private var shouldReturnNetworkError = false
    private var isResponseSuccessful = false

    private val stub = NutritionFactsStub

    override suspend fun getNutritionForItem(ingr: String)
            : Resource<NutritionalFactsResponse> {
        return if (!shouldReturnNetworkError) {
            if (isResponseSuccessful) {
                Resource.Success(stub.nutritionalFactsStub)
            } else {
                Resource.Fail(errorMessage = UNKNOWN_ERROR)
            }
        } else {
            Resource.Fail(errorMessage = NETWORK_ERROR)
        }
    }

    override suspend fun processAllNutritionRequests(ingrs: List<String>)
            : Flow<Resource<NutritionalFactsResponse>> {
        return flow{

            val ingredientDetails = processIngredientDetails(ingrs[0])

            stub.nutritionalFactsStub.foodName = ingredientDetails.first
            stub.nutritionalFactsStub.weight = ingredientDetails.second.toString()
            stub.nutritionalFactsStub.unit = ingredientDetails.third

            emit(Resource.Success(stub.nutritionalFactsStub))
        }
    }

    override fun processIngredientDetails(ingr: String): Triple<String, Int, String> {
        return Triple("carrot", 50, "g")
    }

    override suspend fun getTotalDailyNutrition(request: IngredientsRequest)
            : Resource<ArrayList<Nutrient>> {
        return if (!shouldReturnNetworkError) {
            if (isResponseSuccessful) {
                Resource.Success(totalNutrientsStub.getTotalNutrientsList())
            } else {
                Resource.Fail(errorMessage = UNKNOWN_ERROR)
            }
        } else {
            Resource.Fail(errorMessage = NETWORK_ERROR)
        }
    }

}