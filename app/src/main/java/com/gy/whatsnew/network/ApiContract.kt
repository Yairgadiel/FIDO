package com.gy.whatsnew.network

import com.gy.whatsnew.data.model.ArticlesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "97ee7caa18054e9b881b740f40c1e69c"

interface ApiContract {

    @GET("everything")
    suspend fun getArticles(
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("q") query: String = "Israel",
    ) : Response<ArticlesResponse>
}