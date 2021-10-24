package com.android.viewmodeltesting.remote

import com.android.viewmodeltesting.data.NewsResponse
import com.android.viewmodeltesting.common.util.Status
import com.android.viewmodeltesting.common.util.Result

open class NewsRepository(private val newsInterface: NewsInterface) {

    suspend fun getNewsSourceList(pageNumber: Int): Result<NewsResponse> {
        return try {
            val response = newsInterface.getNewsSourceList(pageNumber)
            if (response.isSuccessful) {
                Result(Status.SUCCESS, response.body())
            } else {
                Result(Status.ERROR, null)
            }
        } catch (e: Exception) {
            Result(Status.ERROR, null)
        }
    }
}