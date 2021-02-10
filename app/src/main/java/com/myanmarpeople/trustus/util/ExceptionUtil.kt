package com.myanmarpeople.trustus.util

import com.google.gson.Gson
import com.myanmarpeople.trustus.model.ErrorModel
import retrofit2.Response

object ExceptionUtil {
    fun <T> getErrorMessageFromResponse(response: Response<T>?): ErrorModel {
        val gson = Gson()
        return gson.fromJson<ErrorModel>(response?.errorBody()?.string(), ErrorModel::class.java)
    }
}