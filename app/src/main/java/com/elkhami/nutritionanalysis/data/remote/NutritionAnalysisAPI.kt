package com.elkhami.nutritionanalysis.data.remote

import com.elkhami.nutritionanalysis.BuildConfig
import com.elkhami.nutritionanalysis.data.model.IngredientsRequest
import com.elkhami.nutritionanalysis.data.model.NutritionalFactsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by A.Elkhami on 27,September,2021
 */
interface NutritionAnalysisAPI {

    @GET("/api/nutrition-data")
    suspend fun getNutritionForItem(
        @Query("app_id") app_id: String = BuildConfig.APP_ID,
        @Query("app_key") app_key: String = BuildConfig.APP_KEY,
        @Query("ingr") ingr: String
    ): Response<NutritionalFactsResponse>

    @POST("/api/nutrition-details")
    suspend fun getNutritionForList(
        @Query("app_id") app_id: String = BuildConfig.APP_ID,
        @Query("app_key") app_key: String = BuildConfig.APP_KEY,
        @Body body: IngredientsRequest
    ): Response<NutritionalFactsResponse>
}