package com.elkhami.nutritionanalysis.data.repository

import com.elkhami.nutritionanalysis.data.model.IngredientsRequest
import com.elkhami.nutritionanalysis.data.model.NutritionalFactsResponse
import com.elkhami.nutritionanalysis.data.remote.NutritionAnalysisAPI
import com.elkhami.nutritionanalysis.other.Constants.MAX_CONCURRENT_REQUESTS
import com.elkhami.nutritionanalysis.other.Constants.NETWORK_ERROR
import com.elkhami.nutritionanalysis.other.Constants.UNKNOWN_ERROR
import com.elkhami.nutritionanalysis.other.Resource
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by A.Elkhami on 27,September,2021
 */
class NutritionAnalysisRepository @Inject constructor(
    private val api: NutritionAnalysisAPI
) : Repository {

    override suspend fun getNutritionForItem(ingr: String): Resource<NutritionalFactsResponse>{
        return try {
            val response = api.getNutritionForItem(ingr = ingr)

            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                }?:  Resource.Fail(errorMessage = UNKNOWN_ERROR)
            }else {
                return Resource.Fail(errorMessage = response.message())
            }

        }catch (e: Exception){
            return Resource.Fail(errorMessage = NETWORK_ERROR)
        }
    }

    @FlowPreview
    override suspend fun processAllNutritionRequests(ingrs: List<String>)
            : Flow<Pair<String, Resource<NutritionalFactsResponse>>> {
        return ingrs
            .asFlow()
            .flatMapMerge(concurrency = MAX_CONCURRENT_REQUESTS) { ingr ->
                flow {
                    emit(ingr to getNutritionForItem(ingr))
                }
            }
    }

    override suspend fun getNutritionForList(request: IngredientsRequest)
            : Resource<NutritionalFactsResponse> {

        return try {
            val response = api.getNutritionForList(body = request)

            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                }?:  Resource.Fail(errorMessage = UNKNOWN_ERROR)
            }else {
                return Resource.Fail(errorMessage = response.message())
            }

        }catch (e: Exception){
            return Resource.Fail(errorMessage = NETWORK_ERROR)
        }
    }
}