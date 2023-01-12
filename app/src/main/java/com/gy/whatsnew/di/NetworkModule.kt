package com.gy.whatsnew.di

import com.gy.whatsnew.network.ApiContract
import com.gy.whatsnew.network.ApiHelper
import com.gy.whatsnew.network.ApiHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideBaseURL() = "https://newsapi.org/v2/"

    @Singleton
    @Provides
    fun provideRetrofit(BASE_URL: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiHelper(apiHelperImpl: ApiHelperImpl) : ApiHelper {
        return apiHelperImpl
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) : ApiContract {
        return retrofit.create(ApiContract::class.java)
    }
}