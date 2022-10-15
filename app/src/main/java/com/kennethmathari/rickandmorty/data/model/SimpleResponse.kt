package com.kennethmathari.rickandmorty.data.model

import retrofit2.Response

data class SimpleResponse<T>(
    val status: Status,
    val data: Response<T>?,
    val exception: Exception?,
) {

    companion object{
        fun <T> success(data: Response<T>): SimpleResponse<T> {
            return SimpleResponse(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(exception: Exception): SimpleResponse<T> {
            return SimpleResponse(
                Status.FAILURE,
                null,
                exception
            )
        }
    }


    sealed class Status {
        object SUCCESS : Status()
        object FAILURE : Status()
    }

    val failed: Boolean
        get() = status == Status.FAILURE

    val isSuccessful: Boolean
        get() = !failed && this.data?.isSuccessful == true && status == Status.SUCCESS

    val body: T
        get() = this.data!!.body()!!
}
