package com.myanmarpeople.trustus.model

import com.google.gson.annotations.SerializedName


data class ErrorModel(
    /*   @SerializedName("errors")
       val errors: Errors,*/
    @SerializedName("message")
    val message: String
)
