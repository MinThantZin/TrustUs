package com.myanmarpeople.trustus.model


class NetWorkResourceModel<out T> private constructor(
     val status: Status,
    val data: T?,
    val appException: AppException?
) {
    enum class Status {
        SUCCESS, ERROR, LOADING
    }
    companion object {
        fun <T> Success(data: T?): NetWorkResourceModel<T> {
            return NetWorkResourceModel(
                NetWorkResourceModel.Status.SUCCESS,
                data,
                null
            )

        }

        fun <T> error(appException: AppException?): NetWorkResourceModel<T> {

            return NetWorkResourceModel(
                NetWorkResourceModel.Status.ERROR,
                data = null,
                appException = appException
            )
        }

        fun <T> loading(): NetWorkResourceModel<T> {
            return NetWorkResourceModel(
                NetWorkResourceModel.Status.LOADING, null, null
            )

        }
    }

    fun getErrorMessage() = appException?.getErrorMessage()
    fun getErrorCode() = appException?.getErrorCode()


}