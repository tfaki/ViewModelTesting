package com.android.viewmodeltesting.di

import androidx.multidex.BuildConfig
import com.android.viewmodeltesting.remote.NewsInterface
import com.android.viewmodeltesting.remote.NewsRepository
import com.android.viewmodeltesting.common.util.Consts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
object HiltModules {

    @Provides
    fun provideOkHttpClient() =
        if (BuildConfig.DEBUG) {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder()
                .addInterceptor(logger)
                .readTimeout(100, TimeUnit.SECONDS)
                .connectTimeout(100, TimeUnit.SECONDS)
                .build()
        } else {
            OkHttpClient.Builder()
                .readTimeout(100, TimeUnit.SECONDS)
                .connectTimeout(100, TimeUnit.SECONDS)
                .build()
        }

    @Provides
    fun provideRetrofitInterface(okHttpClient: OkHttpClient): NewsInterface = Retrofit.Builder()
        .baseUrl(Consts.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsInterface::class.java)

    @Provides
    fun provideRepository(newsInterface: NewsInterface): NewsRepository = NewsRepository(newsInterface)
}