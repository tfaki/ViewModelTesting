package com.android.viewmodeltesting.remote

import com.android.viewmodeltesting.data.NewsResponse
import com.android.viewmodeltesting.common.util.Consts
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsInterface {

    @GET("v2/top-headlines")
    suspend fun getNewsSourceList(
        @Query("page") pageNumber: Int,
        @Query("country") country: String = Consts.COUNTRY,
        @Query("apiKey") apiKey: String = Consts.API_KEY
    ): Response<NewsResponse>
}