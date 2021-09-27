package com.elkhami.nutritionanalysis.di

import com.elkhami.nutritionanalysis.data.remote.NutritionAnalysisAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

/**
 * Created by A.Elkhami on 27,September,2021
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient
        .Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        )

    @Singleton
    @Provides
    fun provideNutritionAnalysisAPI(okHttpClient: OkHttpClient.Builder) =
        Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build())
            .build()
            .create(NutritionAnalysisAPI::class.java)

}