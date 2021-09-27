package com.elkhami.nutritionanalysis.other

/**
 * Created by A.Elkhami on 27,September,2021
 */
sealed class Resource<T>(
    val data: T? = null,
    val errorMessage: String? = null,
    val status: Status
){
    class Success<T>(data: T?): Resource<T>(data = data, status = Status.SUCCESS)
    class Fail<T>(data: T? = null, errorMessage: String?): Resource<T>(data, errorMessage, Status.FAILED)
    class Loading<T>(): Resource<T>(status = Status.LOADING)
}

enum class Status{
    SUCCESS,
    FAILED,
    LOADING
}