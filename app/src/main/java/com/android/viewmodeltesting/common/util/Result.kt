package com.android.viewmodeltesting.common.util

class Result<out T>(val status: Status, val data: T?) {
    companion object {
        fun <T> success(data: T?): Result<T> = Result(Status.SUCCESS, data)

        fun <T> loading(message: String?): Result<T> = Result(Status.LOADING, null)

        fun <T> error(message: String?): Result<T> = Result(Status.ERROR, null)
    }
}

enum class Status {
    SUCCESS,
    LOADING,
    ERROR
}